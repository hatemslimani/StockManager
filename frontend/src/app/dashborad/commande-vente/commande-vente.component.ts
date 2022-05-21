import { Component, OnInit } from '@angular/core';
import { BreadcrumbItem } from 'src/app/model/IbreadCrumb';
import { CommandeVente, LigneCommandeVente } from 'src/app/model/commande-vente.model';
import { Customer } from 'src/app/model/customer.model';
import { Product } from 'src/app/model/product.model';
import { CategoryServiceService } from 'src/app/service/category-service.service';
import { CommandeVenteService } from 'src/app/service/commande-vente.service';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
    selector: 'app-commande-vente',
    templateUrl: './commande-vente.component.html',
    styleUrls: ['./commande-vente.component.scss']
})
export class CommandeVenteComponent implements OnInit {
    commandes: CommandeVente[] = [];
    selectedCommande: CommandeVente | null = null;
    lignesCommande: LigneCommandeVente[] = [];
    clients: Customer[] = [];
    produits: Product[] = [];
    itemSelected: number = NaN;
    showlist: boolean = false;

    breadcrumbItems: BreadcrumbItem[] = [
        { label: 'Accueil', link: '/' },
        { label: 'Commandes Clients' }
    ];

    columns = [
        { field: 'id', title: 'ID', hidden: true },
        { field: 'reference', title: 'Référence' },
        { field: 'date_commande', title: 'Date Commande', type: 'date' },
        { field: 'client.nom', title: 'Client' },
        { field: 'montant_total', title: 'Montant Total', type: 'currency' },
        { field: 'statut', title: 'Statut' },
        { field: 'date_livraison', title: 'Date Livraison', type: 'date' }
    ];

    buttons = [
        {
            text: '<i class="fas fa-plus"></i>',
            action: () => {
                this.resetForm();
                ($('#addCommande') as any).modal('show');
            }
        },
        {
            text: '<i class="fas fa-edit"></i>',
            className: 'edit-button disabled',
            action: () => {
                if (this.itemSelected) {
                    this.loadCommandeDetails(this.itemSelected);
                }
            }
        },
        {
            text: '<i class="fa-solid fa-download"></i>',
            className: 'edit-button disabled',
            action: () => {
                if (this.itemSelected) {
                    this.loadCommandeDetails(this.itemSelected);
                }
            }
        },
        {
            text: '<i class="fas fa-eye"></i>',
            className: 'view-button disabled',
            action: () => {
                if (this.itemSelected) {
                    this.viewCommandeDetails(this.itemSelected);
                }
            }
        }
    ];

    constructor(
        private commandeVenteService: CommandeVenteService,
        private customerService: CustomerService,
        private categoryService: CategoryServiceService
    ) { }

    ngOnInit() {
        this.loadCommandes();
        this.loadClients();
        this.loadProduits();
    }

    loadCommandes() {
        this.commandeVenteService.getAll().subscribe(data => {
            this.commandes = data;
            this.showlist = true;
        });
    }

    loadClients() {
        this.customerService.getAll().subscribe(data => {
            this.clients = data as Customer[];
        });
    }

    loadProduits() {
        this.categoryService.getAllProduit().subscribe(data => {
            this.produits = data as Product[];
        });
    }

    loadCommandeDetails(id: number) {
        this.commandeVenteService.getById(id).subscribe(commande => {
            this.selectedCommande = commande;
            this.loadLignesCommande(id);
            ($('#editCommande') as any).modal('show');
        });
    }

    loadLignesCommande(commandeId: number) {
        this.commandeVenteService.getLignesCommande(commandeId).subscribe(lignes => {
            this.lignesCommande = lignes;
        });
    }

    viewCommandeDetails(id: number) {
        this.commandeVenteService.getById(id).subscribe(commande => {
            this.selectedCommande = commande;            
            this.loadLignesCommande(id);
            ($('#viewCommande') as any).modal('show');
        });
    }

    createCommande(value: any) {
        const commande: CommandeVente = {
            id: 0,
            reference: this.generateReference(),
            date_commande: new Date(),
            client: value.client_id,
            montant_total: 0,
            statut: 'EN_ATTENTE',
            date_ajout: new Date(),
            date_modification: new Date()
        };

        this.commandeVenteService.create(commande).subscribe({
            next: (newCommande) => {
                this.loadCommandes();
                ($('#addCommande') as any).modal('hide');
            },
            error: (error) => {
                console.error('Erreur lors de la création de la commande:', error);
            }
        });
    }

    updateCommande(value: any) {
        if (!this.selectedCommande) return;

        const updatedCommande: CommandeVente = {
            ...this.selectedCommande,
            client: value.client_id,
            date_livraison: value.date_livraison,
            statut: value.statut,
            date_modification: new Date()
        };

        this.commandeVenteService.update(updatedCommande).subscribe({
            next: () => {
                this.loadCommandes();
                ($('#editCommande') as any).modal('hide');
            },
            error: (error) => {
                console.error('Erreur lors de la mise à jour de la commande:', error);
            }
        });
    }

    addLigneCommande(value: any) {
        if (!this.selectedCommande) return;

        const produit = this.produits.find(p => p.id === value.produit_id);
        if (!produit) return;

        const ligne: LigneCommandeVente = {
            id: 0,
            commande_id: this.selectedCommande.id,
            produit: value.produit_id,
            quantite: value.quantite,
            prix_unitaire: produit.prix_unitaire,
            montant: value.quantite * produit.prix_unitaire
        };

        this.commandeVenteService.addLigneCommande(ligne).subscribe({
            next: () => {
                this.loadLignesCommande(this.selectedCommande!.id);
                ($('#addLigneCommande') as any).modal('hide');
            },
            error: (error) => {
                console.error('Erreur lors de l\'ajout de la ligne:', error);
            }
        });
    }

    deleteLigneCommande(ligneId: number) {
        if (confirm('Êtes-vous sûr de vouloir supprimer cette ligne ?')) {
            this.commandeVenteService.deleteLigneCommande(ligneId).subscribe({
                next: () => {
                    this.loadLignesCommande(this.selectedCommande!.id);
                },
                error: (error) => {
                    console.error('Erreur lors de la suppression de la ligne:', error);
                }
            });
        }
    }

    onRowClick(data: any) {
        this.itemSelected = data['id'];
        $(".edit-button").removeClass("disabled");
        $(".view-button").removeClass("disabled");
    }

    resetForm() {
        this.selectedCommande = null;
        this.lignesCommande = [];
    }

    private generateReference(): string {
        const date = new Date();
        const year = date.getFullYear().toString().substr(-2);
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const random = Math.floor(Math.random() * 10000).toString().padStart(4, '0');
        return `CMD${year}${month}${random}`;
    }
} 