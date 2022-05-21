import { Customer } from "./customer.model";
import { Product } from "./product.model";

export interface CommandeVente {
    id: number;
    reference: string;
    date_commande: Date;
    date_livraison?: Date;
    statut: 'EN_ATTENTE' | 'VALIDEE' | 'LIVREE' | 'ANNULEE';
    client: Customer;
    montant_total: number;
    date_ajout: Date;
    date_modification: Date;
}

export interface LigneCommandeVente {
    id: number;
    commande_id: number;
    produit: Product;
    quantite: number;
    prix_unitaire: number;
    montant: number;
} 