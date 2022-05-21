import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Notification } from '../model/notification.model';

@Injectable({
    providedIn: 'root'
})
export class NotificationService {
    private uri: string = "http://localhost:9191/";
    private unreadCount = new BehaviorSubject<number>(0);

    constructor(private http: HttpClient) {
        this.loadNotifications();
    }

    getAll(): Observable<Notification[]> {
        return this.http.get<Notification[]>(this.uri + "notifications/");
    }

    getUnread(): Observable<Notification[]> {
        return this.http.get<Notification[]>(this.uri + "notifications/unread");
    }

    markAsRead(id: number): Observable<any> {
        return this.http.put(this.uri + "notifications/read/" + id, {});
    }

    markAllAsRead(): Observable<any> {
        return this.http.put(this.uri + "notifications/read-all", {});
    }

    delete(id: number): Observable<any> {
        return this.http.delete(this.uri + "notifications/" + id);
    }

    getUnreadCount(): Observable<number> {
        return this.unreadCount.asObservable();
    }

    private loadNotifications() {
        this.getUnread().subscribe(notifications => {
            this.unreadCount.next(notifications.length);
        });
    }
} 