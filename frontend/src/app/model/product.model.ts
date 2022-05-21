export interface Product {
    id: number;
    code: string;
    nom: string;
    description?: string;
    prix_unitaire: number;
    prix_achat: number;
    quantite_stock: number;
    image: string;
    code_barres?: string;
    poids?: number;
    unite_mesure?: string;
    statut: boolean;
    marque?: string;
    date_expiration?: Date;
    origine?: string;
    dimensions?: string;
    id_fournisseur: number;
    id_categorie: number;
    date_ajout: Date;
    date_modification: Date;
} 