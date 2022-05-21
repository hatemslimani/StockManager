export interface PurchaseOrder {
    id: number;
    code: string;
    date_commande: Date;
    total_commande: number;
    statut: string;
    id_fournisseur: number;
    date_ajout: Date;
    date_modification: Date;
} 