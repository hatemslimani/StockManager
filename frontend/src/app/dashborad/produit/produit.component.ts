import { Component, OnInit } from '@angular/core';
import { BreadcrumbItem } from 'src/app/model/IbreadCrumb';
import { CategoryServiceService } from 'src/app/service/category-service.service';

@Component({
  selector: 'app-produit',
  templateUrl: './produit.component.html',
  styleUrls: ['./produit.component.scss'],
})

export class ProduitComponent implements OnInit {
  listcat: any;
  itemSelected: number = NaN;
  showlist: boolean = false;
  listCategory: any;
  file: any = null;
  breadcrumbItems: BreadcrumbItem[] = [
    { label: 'Accueil', link: '/' },
    { label: 'Produit', link: '/produit' },
  ];

  columns = [
    { field: 'id', title: 'ID', hidden: true },
    { field: 'code', title: 'Code' },
    { field: 'nom', title: 'Produit' },
    { field: 'image', title: 'Photo', type: 'image' },
    { field: 'prix_unitaire', title: 'Prix unitaire', type: 'currency', currency: 'dt' },
    { field: 'quantite_stock', title: 'Stock' },
    { field: 'marque', title: 'Marque' },
    { field: 'date_expiration', title: 'Expire le', type: 'date' },
    { field: 'date_ajout', title: 'Ajouté le' , type: 'date' }
  ];

  buttons = [
    {
      text: '<i class="fas fa-plus"></i>',
      action: () => {
        this.getAllCategory();
        ($('#add') as any).modal('show');
      }
    },
    {
      text: '<i class="fas fa-trash-alt"></i>',
      className: 'delete-button disabled',
      action: () => {
        if (this.itemSelected) {
          ($('#deleteProduit') as any).modal('show');
        }
      }
    }
  ];

  constructor(private catService: CategoryServiceService) { }

  ngOnInit() {
    this.catService.getAllProduit().subscribe((data) => {
      console.log(data);

      this.showlist = true;
      this.listcat = data;
    });
  }

  getAllProduit() {
    this.catService.getAllProduit().subscribe((data) => {
      this.listcat = data;
      this.showlist = true;
    });
  }


  addProduct(value: any) {
    const product = {
      id: 0,
      code: value.code,
      nom: value.nom,
      description: value.description,
      prix_unitaire: value.prix_unitaire,
      prix_achat: value.prix_achat,
      quantite_stock: value.quantite_stock,
      code_barres: value.code_barres,
      poids: value.poids,
      unite_mesure: value.unite_mesure,
      statut: "active",
      marque: value.marque,
      date_expiration: value.date_expiration,
      origine: value.origine,
      dimensions: value.dimensions,
      id_categorie: value.id_categorie,
      date_ajout: new Date(),
      date_modification: new Date()
    };

    if (!this.file) {
      console.error('Image obligatoire');
      return;
    }

    const formData = new FormData();
    formData.append('produit', JSON.stringify(product));
    formData.append('img', this.file);

    this.catService.addProduct(formData).subscribe({
      next: () => {
        this.getAllProduit();
        ($('#add') as any).modal('hide');
        $('#addProduct').trigger('reset');
        this.file = null;
      },
      error: (error) => {
        console.error('Erreur lors de l\'ajout du produit:', error);
      }
    });
  }

  onSelect(event: any) {
    this.file = event.target.files[0];
  }

  deleteProduit() {
    this.catService.deleteProduit(this.itemSelected).subscribe(() => {
      (<any>$('#deleteProduit')).modal('hide');
      this.itemSelected = NaN;
      this.getAllProduit();
    });
  }

  getAllCategory() {
    this.catService.getAllCategory().subscribe((data) => {
      this.listCategory = data;
    });
  }

  onRowClick(data: any) {
    this.itemSelected = data["id"];
    $(".delete-button").removeClass("disabled");
    $(".edit-button").removeClass("disabled");
  }
}
