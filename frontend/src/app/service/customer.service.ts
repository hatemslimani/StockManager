import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../model';

@Injectable({
    providedIn: 'root'
})
export class CustomerService {
    private uri: string = "http://localhost:9191/";

    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get(this.uri + "customer/");
    }

    add(customer: Customer) {
        return this.http.post(this.uri + "customer/add", customer);
    }

    delete(id: number) {
        return this.http.delete(this.uri + "customer/delete/" + id);
    }

    getById(id: number) {
        return this.http.get(this.uri + "customer/" + id);
    }

    update(customer: Customer) {
        return this.http.put(this.uri + "customer/update/" + customer.id, customer);
    }
} 