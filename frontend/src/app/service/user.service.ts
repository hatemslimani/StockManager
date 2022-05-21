import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    private uri: string = "http://localhost:9191/";

    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get(this.uri + "users/");
    }

    add(user: User) {
        return this.http.post(this.uri + "users/add", user);
    }

    delete(id: number) {
        return this.http.delete(this.uri + "users/delete/" + id);
    }

    getById(id: number) {
        return this.http.get(this.uri + "users/" + id);
    }

    update(user: User) {
        return this.http.put(this.uri + "users/update/" + user.id, user);
    }

    toggleStatus(id: number) {
        return this.http.put(this.uri + "users/toggle-status/" + id, {});
    }

    uploadProfileImage(userId: number, file: File): Observable<any> {
        const formData = new FormData();
        formData.append('file', file);
        return this.http.post(this.uri + `users/${userId}/upload-image`, formData);
    }

    getProfileImageUrl(userId: number): string {
        return this.uri + `users/${userId}/image`;
    }
} 