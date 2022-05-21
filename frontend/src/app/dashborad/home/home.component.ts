import { Component, OnInit } from '@angular/core';
import { CategoryServiceService } from 'src/app/service/category-service.service';
import { CommandeAchatService } from 'src/app/service/commande-achat.service';
import { CustomerService } from 'src/app/service/customer.service';
import { FournisseurServiceService } from 'src/app/service/fournisseur-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  stats = {
    totalProducts: 0,
    totalCategories: 0,
    totalSuppliers: 0,
    totalCustomers: 0,
    totalOrders: 0,
    pendingOrders: 0,
    acceptedOrders: 0,
    rejectedOrders: 0
  };

  constructor(
    private categoryService: CategoryServiceService,
    private commandeService: CommandeAchatService,
    private customerService: CustomerService,
    private fournisseurService: FournisseurServiceService
  ) { }

  ngOnInit(): void {
    this.loadStats();
  }

  loadStats() {
    this.categoryService.getAllProduit().subscribe(data => {
      this.stats.totalProducts = Array.isArray(data) ? data.length : 0;
    });

    this.categoryService.getAllCategory().subscribe(data => {
      this.stats.totalCategories = Array.isArray(data) ? data.length : 0;
    });

    this.fournisseurService.getAll().subscribe(data => {
      this.stats.totalSuppliers = Array.isArray(data) ? data.length : 0;
    });

    // Charger le nombre de clients
    this.customerService.getAll().subscribe(data => {
      this.stats.totalCustomers = Array.isArray(data) ? data.length : 0;
    });

    this.commandeService.getAll().subscribe(data => {
      if (Array.isArray(data)) {
        this.stats.totalOrders = data.length;
        this.stats.pendingOrders = data.filter(cmd => cmd.statut === 'EN_ATTENTE').length;
        this.stats.acceptedOrders = data.filter(cmd => cmd.statut === 'VALIDEE').length;
        this.stats.rejectedOrders = data.filter(cmd => cmd.statut === 'ANNULEE').length;
      }
    });
  }
}
