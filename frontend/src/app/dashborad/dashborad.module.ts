import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DataTablesModule } from 'angular-datatables';
import { SharedModule } from '../shared/shared.module';
import { CategoryComponent } from './category/category.component';
import { CommandeAchatComponent } from './commande-achat/commande-achat.component';
import { CommandeVenteComponent } from './commande-vente/commande-vente.component';
import { CustomerComponent } from './customer/customer.component';
import { DashboradComponent } from './dashborad.component';
import { FournisseurComponent } from './fournisseur/fournisseur.component';
import { HomeComponent } from './home/home.component';
import { NotificationComponent } from './notification/notification.component';
import { ProduitComponent } from './produit/produit.component';
import { ProfileComponent } from './profile/profile.component';
import { UserComponent } from './user/user.component';

@NgModule({
    declarations: [
        DashboradComponent,
        CategoryComponent,
        FournisseurComponent,
        HomeComponent,
        CommandeAchatComponent,
        ProduitComponent,
        FournisseurComponent,
        CustomerComponent,
        UserComponent,
        ProfileComponent,
        NotificationComponent,
        CommandeVenteComponent
    ],
    imports: [
        CommonModule,
        SharedModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        DataTablesModule
    ]
})
export class DashboradModule { } 