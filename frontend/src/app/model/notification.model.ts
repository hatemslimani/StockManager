export interface Notification {
    id: number;
    title: string;
    message: string;
    type: 'INFO' | 'WARNING' | 'ERROR';
    isRead: boolean;
    date_ajout: Date;
    date_lecture?: Date;
    link?: string;
} 