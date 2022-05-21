export interface User {
    id: number;
    username: string;
    email: string;
    password?: string;
    role: 'ADMIN' | 'USER';
    statut: boolean;
    date_ajout: Date;
    date_modification: Date;
} 