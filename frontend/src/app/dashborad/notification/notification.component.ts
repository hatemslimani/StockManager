import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Notification } from 'src/app/model/notification.model';
import { NotificationService } from 'src/app/service/notification.service';

@Component({
    selector: 'app-notification',
    templateUrl: './notification.component.html',
    styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit {
    notifications: Notification[] = [];
    unreadCount: number = 0;
    isOpen: boolean = false;

    constructor(
        private notificationService: NotificationService,
        private router: Router
    ) { }

    ngOnInit() {
        this.loadNotifications();
        this.notificationService.getUnreadCount().subscribe(count => {
            this.unreadCount = count;
        });
    }

    @HostListener('document:click', ['$event'])
    onDocumentClick(event: MouseEvent) {
        const dropdownElement = (event.target as HTMLElement).closest('.notification-dropdown');
        const toggleElement = (event.target as HTMLElement).closest('.notification-badge');

        if (!dropdownElement && !toggleElement) {
            this.isOpen = false;
        }
    }

    toggleDropdown(event: Event) {
        event.preventDefault();
        event.stopPropagation();
        this.isOpen = !this.isOpen;
    }

    loadNotifications() {
        this.notificationService.getAll().subscribe(data => {
            this.notifications = data;
        });
    }

    markAsRead(notification: Notification) {
        this.notificationService.markAsRead(notification.id).subscribe(() => {
            notification.isRead = true;
            if (notification.link) {
                this.router.navigate([notification.link]);
            }
        });
    }

    markAllAsRead() {
        this.notificationService.markAllAsRead().subscribe(() => {
            this.notifications.forEach(n => n.isRead = true);
            this.unreadCount = 0;
        });
    }

    deleteNotification(id: number) {
        this.notificationService.delete(id).subscribe(() => {
            this.notifications = this.notifications.filter(n => n.id !== id);
        });
    }

    getNotificationIcon(type: string): string {
        switch (type) {
            case 'WARNING': return 'fa-exclamation-triangle text-warning';
            case 'ERROR': return 'fa-times-circle text-danger';
            default: return 'fa-info-circle text-info';
        }
    }
} 