import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
    selector: 'app-delete-modal',
    templateUrl: './delete-modal.component.html',
    styleUrls: ['./delete-modal.component.scss']
})
export class DeleteModalComponent {
    @Input() modalId: string = 'deleteModal';
    @Input() title: string = 'Confirmation de suppression';
    @Input() message: string = 'Êtes-vous sûr de vouloir supprimer cet élément ?';
    @Input() itemName?: string;
    @Input() disabled: boolean = false;
    @Output() confirmDelete = new EventEmitter<void>();

    onDelete() {
        this.confirmDelete.emit();
    }
} 