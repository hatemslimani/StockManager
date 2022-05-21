import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DataTablesModule } from 'angular-datatables';
import { BreadcrumbComponent } from './components/breadcrumb/breadcrumb.component';
import { DataTableComponent } from './components/data-table/data-table.component';
import { DeleteModalComponent } from './components/delete-modal/delete-modal.component';

@NgModule({
    declarations: [
        BreadcrumbComponent,
        DataTableComponent,
        DeleteModalComponent
    ],
    imports: [
        CommonModule,
        RouterModule,
        DataTablesModule,
        FormsModule
    ],
    exports: [
        BreadcrumbComponent,
        DataTableComponent,
        DeleteModalComponent
    ]
})
export class SharedModule { } 