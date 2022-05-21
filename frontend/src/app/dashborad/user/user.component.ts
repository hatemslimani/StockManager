import { Component, OnInit } from '@angular/core';
import { BreadcrumbItem } from 'src/app/model/IbreadCrumb';
import { User } from 'src/app/model/user.model';
import { UserService } from 'src/app/service/user.service';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
    list_users: any;
    user: any = null;
    itemSelected: number = NaN;
    showlist: boolean = false;

    breadcrumbItems: BreadcrumbItem[] = [
        { label: 'Accueil', link: '/' },
        { label: 'Utilisateurs' }
    ];

    columns = [
        { field: 'id', title: 'ID', hidden: true },
        { field: 'username', title: 'Nom d\'utilisateur' },
        { field: 'email', title: 'Email' },
        { field: 'role', title: 'Rôle' },
        { field: 'statut', title: 'Statut', type: 'boolean' },
        { field: 'date_ajout', title: 'Date ajout', type: 'date' }
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
                    this.getUserById();
                }
            }
        },
        {
            text: '<i class="fas fa-power-off"></i>',
            className: 'toggle-button disabled',
            action: () => {
                if (this.itemSelected) {
                    this.toggleUserStatus();
                }
            }
        },
        {
            text: '<i class="fas fa-trash"></i>',
            className: 'delete-button disabled',
            action: () => {
                if (this.itemSelected) {
                    ($('#deleteUser') as any).modal('show');
                }
            }
        }
    ];

    constructor(private userService: UserService) { }

    ngOnInit() {
        this.getAll();
    }

    getAll() {
        this.userService.getAll().subscribe(data => {
            this.list_users = data;
            this.showlist = true;
        });
    }

    getUserById() {
        this.userService.getById(this.itemSelected).subscribe(data => {
            this.user = data;
            ($('#editUser') as any).modal('show');
        });
    }

    addUser(value: any) {
        const user: User = {
            id: 0,
            username: value.username,
            email: value.email,
            password: value.password,
            role: value.role,
            statut: true,
            date_ajout: new Date(),
            date_modification: new Date()
        };

        this.userService.add(user).subscribe({
            next: () => {
                this.getAll();
                ($('#add') as any).modal('hide');
                $("#addUser").trigger("reset");
            },
            error: (error) => {
                console.error('Erreur lors de l\'ajout de l\'utilisateur:', error);
            }
        });
    }

    edit(value: User) {
        value.id = this.itemSelected;
        value.date_modification = new Date();

        this.userService.update(value).subscribe(() => {
            this.getAll();
            this.itemSelected = NaN;
            ($('#editUser') as any).modal('hide');
            $("#editUserForm").trigger("reset");
        });
    }

    toggleUserStatus() {
        this.userService.toggleStatus(this.itemSelected).subscribe(() => {
            this.getAll();
        });
    }

    onRowClick(data: any) {
        this.itemSelected = data['id'];
        this.user = data;
        $(".edit-button").removeClass("disabled");
        $(".toggle-button").removeClass("disabled");
        $(".delete-button").removeClass("disabled");
    }

    delete() {
        this.userService.delete(this.itemSelected).subscribe({
            next: () => {
                this.getAll();
                ($('#deleteUser') as any).modal('hide');
                this.itemSelected = NaN;
            },
            error: (error) => {
                console.error('Erreur lors de la suppression de l\'utilisateur:', error);
            }
        });
    }
} 