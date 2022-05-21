import { Component, Input } from '@angular/core';

interface BreadcrumbItem {
    label: string;
    link?: string;
}

@Component({
    selector: 'app-breadcrumb',
    templateUrl: './breadcrumb.component.html',
    styleUrls: ['./breadcrumb.component.scss']
})
export class BreadcrumbComponent {
    @Input() items: BreadcrumbItem[] = [];
} 