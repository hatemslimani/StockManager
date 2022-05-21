export interface PurchaseOrderDetail {
    id?: number;
    prix_achat: number;
    quantite_achate: number;
    id_commande_achat: number;
    id_produit: number;
    date_ajout: Date;
    date_modification: Date;
} 