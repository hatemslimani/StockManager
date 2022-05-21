import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { saveAs } from 'file-saver';
import { ToastrService } from 'ngx-toastr';
import { PurchaseOrder, PurchaseOrderDetail } from 'src/app/model';
import { BreadcrumbItem } from 'src/app/model/IbreadCrumb';
import { CategoryServiceService } from 'src/app/service/category-service.service';
import { CommandeAchatService } from 'src/app/service/commande-achat.service';
import { FournisseurServiceService } from 'src/app/service/fournisseur-service.service';

@Component({
  selector: 'app-commande-achat',
  templateUrl: './commande-achat.component.html',
  styleUrls: ['./commande-achat.component.scss']
})
export class CommandeAchatComponent implements OnInit {

  list_orders: any;
  listProduit: any;
  selectedOrder: any = null;
  listFournis: any;
  itemSelected: number = NaN;
  showlist: boolean = false;
  commandeFrom = this.fb.group({
    id_fournisseur: [''],
    produits: this.fb.array([this.createProduit()])
  });
  breadcrumbItems: BreadcrumbItem[] = [
    { label: 'Accueil', link: '/' },
    { label: "Commande D'achat", link: 'achat' },
  ];

  columns = [
    { field: 'id', title: 'ID', hidden: true },
    { field: 'code', title: 'Code' },
    { field: 'total_commande', title: 'Total', type: 'currency', currency: 'dt' },
    { field: 'statut', title: 'Statut' },
    { field: 'date_ajout', title: 'Date ajout' ,type:"date" },
    { field: 'date_modification', title: 'Dernière modification', type:"date"  }
  ];

  buttons = [
    {
      text: '<i class="fas fa-plus"></i>',
      action: () => {
        ($('#add') as any).modal('show');
      }
    },
    {
      text: '<i class="fas fa-eye"></i>',
      className: 'view-button disabled',
      action: () => {
        if (this.itemSelected) {
          this.getCommandeById();
        }
      }
    },
    {
      text: '<i class="fa-solid fa-download"></i>',
      className: 'download-button disabled',
      action: () => {
        if (this.itemSelected) {
          this.downloadPDF(this.itemSelected);
        }
      }
    },
    {
      text: '<i class="fas fa-trash-alt"></i>',
      className: 'delete-button disabled',
      action: () => {
        if (this.itemSelected) {
          ($('#deleteCommande') as any).modal('show');
        }
      }
    }
  ];

  constructor(private commande: CommandeAchatService,
    private categService: CategoryServiceService,
    private forunisService: FournisseurServiceService,
    private fb: FormBuilder,
    private toastr: ToastrService) {

  }

  ngOnInit() {
    this.commande.getAll().subscribe((data) => {
      this.showlist = true;
      this.list_orders = data;
    })
    this.getAllFournisseur();
    this.getAllProduit();
  }

  get produits() {
    return this.commandeFrom.get('produits') as FormArray
  }

  addProduit() {
    this.produits.push(this.createProduit())
  }

  getAll() {
    this.commande.getAll().subscribe((data) => {
      this.list_orders = data;
      this.showlist = true;
    })
  }

  getCommandeById() {
    this.commande.getById(this.itemSelected).subscribe((data) => {
      console.log(data);
      this.selectedOrder = data;
      ($('#chechekStatus') as any).modal('show');
    })
  }

  getAllProduit() {
    this.categService.getAllProduit().subscribe((data) => {
      this.listProduit = data;
    })
  }

  getAllFournisseur() {
    this.forunisService.getAll().subscribe((data) => {
      console.log(data);

      this.listFournis = data;
    })
  }

  addcommande() {
    const order: PurchaseOrder = {
      id: 0,
      code: this.generateOrderCode(),
      date_commande: new Date(),
      total_commande: this.calculateTotal(),
      statut: 'EN_ATTENTE',
      id_fournisseur: this.commandeFrom.value.id_fournisseur,
      date_ajout: new Date(),
      date_modification: new Date()
    };

    const orderDetails: PurchaseOrderDetail[] = this.commandeFrom.value.produits.map((p: any) => ({
      prix_achat: p.prix_achat,
      quantite_achate: p.quantiteDispo_produit,
      id_commande_achat: 0,
      id_produit: p.id,
      date_ajout: new Date(),
      date_modification: new Date()
    }));

    const orderData = {
      order: order,
      orderDetails: orderDetails
    };

    this.commande.addCommandeAchat(orderData).subscribe({
      next: () => {
        this.getAll();
        ($('#add') as any).modal('hide');
        this.toastr.success('Commande ajoutée', 'Succès');
        this.commandeFrom.reset();
        this.commandeFrom.setControl('produits', this.fb.array([this.createProduit()]));
      },
      error: (error) => {
        console.error('Erreur lors de l\'ajout de la commande:', error);
        this.toastr.error('Erreur lors de l\'ajout de la commande', 'Erreur');
      }
    });
  }

  delete() {
    this.commande.delete(this.itemSelected).subscribe(() => {
      (<any>$('#deleteCateg')).modal('hide');
      this.getAll();
      this.itemSelected = NaN;
      this.toastr.success('Commande supprimier', 'Success');
    });
  }

  edit(value: PurchaseOrder) {
    value.id = Number(this.itemSelected);
    this.commande.edit(this.itemSelected, value).subscribe(() => {
      this.getAll();
      this.itemSelected = NaN;
      (<any>$('#editCommande')).modal('hide');
      $("#editCatgform").trigger("reset");
    })
  }

  createProduit(): FormGroup {
    return this.fb.group({
      id: [''],
      quantiteDispo_produit: [''],
      prix_achat: ['']
    });
  }

  private generateOrderCode(): string {
    return 'CMD-' + new Date().getTime();
  }

  private calculateTotal(): number {
    return this.commandeFrom.value.produits.reduce((total: number, p: any) => {
      return total + (p.prix_achat * p.quantiteDispo_produit);
    }, 0);
  }

  accepterCommande() {
    this.commande.accepterCommande(this.itemSelected).subscribe((data) => {
      this.getAll();
      this.toastr.success('Commande acceptée', 'Success');
      (<any>$('#chechekStatus')).modal('hide');
    })
  }

  refuserCommande() {
    this.commande.refuserCommande(this.itemSelected).subscribe((data) => {
      this.getAll();
      this.toastr.success('Commande refusée', 'Success');
      (<any>$('#chechekStatus')).modal('hide');
    })
  }

  downloadPDF(id: number) {
    this.commande.downloadPDF(id).subscribe({
      next: (blob: Blob) => {
        saveAs(blob, `commande-${id}.pdf`);
      },
      error: (error) => {
        console.error('Erreur lors du téléchargement du PDF:', error);
      }
    });
  }

  onRowClick(data: any) {
    this.itemSelected = data['id'];
    $(".delete-button").removeClass("disabled");
    $(".view-button").removeClass("disabled");
    $(".download-button").removeClass("disabled");
  }

  removeProduit(index: number) {
    this.produits.removeAt(index);
  }
}
