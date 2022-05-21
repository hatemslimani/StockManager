import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../service/notification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashborad',
  templateUrl: './dashborad.component.html',
  styleUrls: ['./dashborad.component.scss']
})
export class DashboradComponent implements OnInit {
  isSidenavCollapsed: boolean = false;
  isMobileMenuOpen: boolean = false;
  unreadCount: number = 0;

  constructor(private notificationService: NotificationService, private route :Router) { }

  ngOnInit() {
    this.notificationService.getUnreadCount().subscribe(count => {
      this.unreadCount = count;
    });
  }

  toggleSidenav() {
    if (window.innerWidth <= 768) {
      this.isMobileMenuOpen = !this.isMobileMenuOpen;
    } else {
      this.isSidenavCollapsed = !this.isSidenavCollapsed;
    }
  }

  toggleNotifications(event: Event) {
    event.preventDefault();
    event.stopPropagation();
  }

  deconnect() {
    this.route.navigate(['/login'])
  }
}
