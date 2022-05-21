import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryComponent } from './dashborad/category/category.component';
import { CommandeAchatComponent } from './dashborad/commande-achat/commande-achat.component';
import { CommandeVenteComponent } from './dashborad/commande-vente/commande-vente.component';
import { CustomerComponent } from './dashborad/customer/customer.component';
import { DashboradComponent } from './dashborad/dashborad.component';
import { FournisseurComponent } from './dashborad/fournisseur/fournisseur.component';
import { HomeComponent } from './dashborad/home/home.component';
import { ProduitComponent } from './dashborad/produit/produit.component';
import { ProfileComponent } from './dashborad/profile/profile.component';
import { UserComponent } from './dashborad/user/user.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  {
    path: "", component: DashboradComponent,
    children: [
      {
        path: "", component: HomeComponent
      },
      {
        path: "category", component: CategoryComponent
      },
      {
        path: "produit", component: ProduitComponent
      },
      {
        path: "fournisseur", component: FournisseurComponent
      },
      {
        path: "achat", component: CommandeAchatComponent
      },
      {
        path: 'customers',
        component: CustomerComponent
      },
      {
        path: 'users',
        component: UserComponent
      },
      {
        path: 'profile',
        component: ProfileComponent
      },
      {
        path: 'ventes',
        component: CommandeVenteComponent
      }
    ]
  }, {
    path: "login", component: LoginComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
