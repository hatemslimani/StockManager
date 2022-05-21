export * from './category.model';
export * from './customer.model';
export * from './product.model';
export * from './purchase-order-detail.model';
export * from './purchase-order.model';
export * from './supplier.model';

// Enums communs
export enum OrderStatus {
    EN_ATTENTE = 'EN_ATTENTE',
    VALIDEE = 'VALIDEE',
    ANNULEE = 'ANNULEE',
    LIVREE = 'LIVREE'
}

export enum UniteMesure {
    UNITE = 'UNITE',
    KG = 'KG',
    LITRE = 'LITRE',
    METRE = 'METRE'
} 