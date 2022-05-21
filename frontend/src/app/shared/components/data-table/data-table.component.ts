import { DatePipe } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
    selector: 'app-data-table',
    templateUrl: './data-table.component.html',
    styleUrls: ['./data-table.component.scss'],
    providers: [DatePipe]
})
export class DataTableComponent {
    @Input() columns: any[] = [];
    @Input() data: any[] = [];
    @Input() buttons: any[] = [];
    @Output() rowClick = new EventEmitter<any>();

    sortColumn: string = '';
    sortDirection: 'asc' | 'desc' = 'asc';
    searchText: string = '';
    currentPage: number = 1;
    pageSize: number = 5;

    constructor(private datePipe: DatePipe) { }

    get filteredData() {
        if (!this.data) return [];
        return this.data
            .filter(item => this.filterItem(item))
            .sort((a, b) => this.sortItems(a, b));
    }

    get paginatedData() {
        const startIndex = (this.currentPage - 1) * this.pageSize;
        return this.filteredData.slice(startIndex, startIndex + this.pageSize);
    }

    get totalPages() {
        return Math.ceil(this.filteredData.length / this.pageSize);
    }

    filterItem(item: any): boolean {
        if (!this.searchText) return true;
        return Object.keys(item).some(key =>
            String(item[key]).toLowerCase().includes(this.searchText.toLowerCase())
        );
    }

    sortItems(a: any, b: any): number {
        if (!this.sortColumn) return 0;

        const valueA = this.getNestedValue(a, this.sortColumn);
        const valueB = this.getNestedValue(b, this.sortColumn);

        if (valueA < valueB) return this.sortDirection === 'asc' ? -1 : 1;
        if (valueA > valueB) return this.sortDirection === 'asc' ? 1 : -1;
        return 0;
    }

    getNestedValue(obj: any, path: string): any {
        return path.split('.').reduce((o, i) => o?.[i], obj);
    }

    sort(column: string) {
        if (this.sortColumn === column) {
            this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
        } else {
            this.sortColumn = column;
            this.sortDirection = 'asc';
        }
    }

    onPageChange(page: number) {
        this.currentPage = page;
    }

    onRowClicked(item: any) {
        this.rowClick.emit(item);
    }

    formatValue(value: any, column: any): string {
        if (!value) return '';

        switch (column.type) {
            case 'date':
                return this.datePipe.transform(value, 'dd/MM/yyyy') || '';
            case 'datetime':
                return this.datePipe.transform(value, 'dd/MM/yyyy HH:mm') || '';
            case 'currency':
                return new Intl.NumberFormat('fr-FR', {
                    style: 'currency',
                    currency: column.currency || 'EUR'
                }).format(value);
            case 'percentage':
                return `${value}%`;
            case 'boolean':
                return value ? 'ACTIF' : 'NonACTIF';
            default:
                return String(value);
        }
    }
} 