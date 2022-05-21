import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/model';
import { BreadcrumbItem } from 'src/app/model/IbreadCrumb';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
    selector: 'app-customer',
    templateUrl: './customer.component.html',
    styleUrls: ['./customer.component.scss']
})
export class CustomerComponent implements OnInit {
    list_customer: any;
    customer: any = null;
    itemSelected: number = NaN;
    showlist: boolean = false;

    breadcrumbItems: BreadcrumbItem[] = [
        { label: 'Accueil', link: '/' },
        { label: 'Clients' }
    ];

    columns = [
        { field: 'id', title: 'ID', hidden: true },
        { field: 'nom', title: 'Nom' },
        { field: 'prenom', title: 'Prénom' },
        { field: 'email', title: 'Email' },
        { field: 'telephone', title: 'Téléphone' },
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
                if (this.itemSelected) {
                    this.getCustomerById();
                }
            }
        },
        {
            text: '<i class="fas fa-trash-alt"></i>',
            className: 'delete-button disabled',
            action: () => {
                if (this.itemSelected) {
                    ($('#deleteCustomer') as any).modal('show');
                }
            }
        }
    ];

    constructor(private customerService: CustomerService) { }

    ngOnInit() {
        this.getAll();
    }

    getAll() {
        this.customerService.getAll().subscribe(data => {
            this.list_customer = data;
            this.showlist = true;
        });
    }

    getCustomerById() {
        this.customerService.getById(this.itemSelected).subscribe(data => {
            this.customer = data;
            ($('#editCustomer') as any).modal('show');
        });
    }

    addCustomer(value: any) {
        const customer: Customer = {
            id: 0,
            nom: value.nom,
            prenom: value.prenom,
            email: value.email,
            telephone: value.telephone,
            adresse: value.adresse,
            ville: value.ville,
            code_postal: value.code_postal,
            pays: value.pays,
            date_ajout: new Date(),
            date_modification: new Date()
        };

        this.customerService.add(customer).subscribe({
            next: () => {
                this.getAll();
                ($('#add') as any).modal('hide');
                $("#addCustomer").trigger("reset");
            },
            error: (error) => {
                console.error('Erreur lors de l\'ajout du client:', error);
            }
        });
    }

    delete() {
        this.customerService.delete(this.itemSelected).subscribe(() => {
            ($('#deleteCustomer') as any).modal('hide');
            this.itemSelected = NaN;
            this.getAll();
        });
    }

    edit(value: Customer) {
        value.id = this.itemSelected;
        value.date_modification = new Date();

        this.customerService.update(value).subscribe(() => {
            this.getAll();
            this.itemSelected = NaN;
            ($('#editCustomer') as any).modal('hide');
            $("#editCustomerForm").trigger("reset");
        });
    }

    onRowClick(data: any) {
        this.itemSelected = data['id'];
        $(".delete-button").removeClass("disabled");
        $(".edit-button").removeClass("disabled");
    }
} 