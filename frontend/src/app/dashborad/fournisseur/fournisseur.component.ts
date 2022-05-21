import { Component, OnInit } from '@angular/core';
import { Supplier } from 'src/app/model';
import { BreadcrumbItem } from 'src/app/model/IbreadCrumb';
import { FournisseurServiceService } from 'src/app/service/fournisseur-service.service';

@Component({
  selector: 'app-fournisseur',
  templateUrl: './fournisseur.component.html',
  styleUrls: ['./fournisseur.component.scss']
})
export class FournisseurComponent implements OnInit {

  list_supplier: any;
  supplier: any = null;
  itemSelected: number = NaN;
  showlist: boolean = false;
  breadcrumbItems: BreadcrumbItem[] = [
    { label: 'Accueil', link: '/' },
    { label: 'Fournisseur' }
  ];

  columns = [
    { field: 'id', title: 'ID', hidden: true },
    { field: 'nom', title: 'Nom' },
    { field: 'telephone', title: 'Téléphone' },
    { field: 'email', title: 'Email' },
    { field: 'ville', title: 'Ville' },
    { field: 'pays', title: 'Pays' }
  ];

  buttons = [
    {
      text: '<i class="fas fa-plus"></i>',
      action: () => {
        ($('#add') as any).modal('show');
      }
    },
    {
      text: '<i class="fas fa-edit"></i>',
      className: 'edit-button disabled',
      action: () => {
        this.getFournisseurById();
      }
    },
    {
      text: '<i class="fas fa-trash-alt"></i>',
      className: 'delete-button disabled',
      action: () => {
        if (this.itemSelected) {
          ($('#deleteSupplier') as any).modal('show');
        }
      }
    }
  ];

  constructor(private fourniss: FournisseurServiceService) { }

  ngOnInit() {
    this.fourniss.getAll().subscribe((data) => {
      this.showlist = true;
      this.list_supplier = data;
    })
  }

  getAll() {
    this.fourniss.getAll().subscribe((data) => {
      this.list_supplier = data;
      this.showlist = true;
    })
  }

  getFournisseurById() {
    this.fourniss.getById(this.itemSelected).subscribe((data) => {
      this.supplier = data;
      ($('#editsupplier') as any).modal('show');
    })
  }

  addFournisseur(value: any) {
    this.fourniss.addFournisseur(value).subscribe((data) => {
      this.getAll();
      (<any>$('#add')).modal('hide');
      $("#addpre").trigger("reset");
    });
  }

  delete() {
    this.fourniss.delete(this.itemSelected).subscribe(() => {
      (<any>$('#deleteSupplier')).modal('hide');
      this.itemSelected = NaN;
      this.getAll();
    });
  }

  edit(value: Supplier) {
    value.id = Number(this.itemSelected);
    this.fourniss.edit(this.itemSelected, value).subscribe(() => {
      this.getAll();
      this.itemSelected = NaN;
      (<any>$('#editsupplier')).modal('hide');
      $("#editCatgform").trigger("reset");
    })
  }

  onRowClick(data: any) {
    this.itemSelected = data['id'];
    $(".delete-button").removeClass("disabled");
    $(".edit-button").removeClass("disabled");
  }
}
