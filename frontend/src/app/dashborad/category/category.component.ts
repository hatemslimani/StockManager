import { Component, OnInit } from '@angular/core';
import { Category } from 'src/app/model';
import { BreadcrumbItem } from 'src/app/model/IbreadCrumb';
import { CategoryServiceService } from 'src/app/service/category-service.service';
@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {

  listcat: any;
  category: any = null;
  itemSelected: number = NaN;
  showlist: boolean = false;
  breadcrumbItems: BreadcrumbItem[] = [
    { label: 'Accueil', link: '/' },
    { label: 'Categorie' }
  ];

  columns = [
    { field: 'id', title: 'ID', hidden: true },
    { field: 'nom', title: 'Categorie' },
    { field: 'statut', title: 'statut' }
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
        if (this.itemSelected) {
          this.getCategoryById();
        }
      }
    },
    {
      text: '<i class="fas fa-trash-alt"></i>',
      className: 'delete-button disabled',
      action: () => {
        if (this.itemSelected) {
          (<any>$('#deleteCateg')).modal('show');
        }
      }
    }
  ];

  constructor(private catService: CategoryServiceService) { }


  ngOnInit() {
    this.getAllCategory();
  }


  getAllCategory() {
    this.catService.getAllCategory().subscribe((data) => {
      this.listcat = data;
      console.log(this.listcat);
      this.showlist = true;
    })
  }

  getCategoryById() {
    this.catService.getById(this.itemSelected).subscribe((data) => {
      console.log(data);
      this.category = data;
      (<any>$('#editCategory')).modal('show');
    })
  }

  addCategory(value: any) {
    const category: Category = {
      nom: value.libCateg,
      id: 0,
      statut: "active",
      date_ajout: new Date(),
      date_modification: new Date()
    };

    this.catService.addCategory(category).subscribe({
      next: () => {
        this.getAllCategory();
        ($('#add') as any).modal('hide');
        $("#addpre").trigger("reset");
      },
      error: (error) => {
        console.error('Erreur lors de l\'ajout de la catégorie:', error);
        //this.toastr.error('Erreur lors de l\'ajout de la catégorie', 'Erreur');
      }
    });
  }

  deleteCategory() {
    if (this.itemSelected) {
      this.catService.deleteCategory(this.itemSelected).subscribe(() => {
        ($('#deleteCateg') as any).modal('hide');
        this.getAllCategory();
        this.itemSelected = NaN;
        this.category = null;
      });
    }
  }

  editCategory(value: Category) {
    value.id = this.itemSelected;
    this.catService.editCategory(value).subscribe(() => {
      this.getAllCategory();
      (<any>$('#editCategory')).modal('hide');
      $("#editCatgform").trigger("reset");
    })
  }

  onRowClick(data: any) {
    this.itemSelected = data["id"];
    console.log(this.itemSelected);
    $(".delete-button").removeClass("disabled");
    $(".edit-button").removeClass("disabled");
  }
}
