export interface Supplier {
    id: number;
    nom: string;
    adresse: string;
    ville: string;
    code_postal: string;
    pays: string;
    telephone: string;
    email: string;
    site_web?: string;
    conditions_paiement?: string;
    date_ajout: Date;
    date_modification: Date;
} 