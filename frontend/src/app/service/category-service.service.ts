import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../model';
@Injectable({
  providedIn: 'root'
})
export class CategoryServiceService {
  private uri: String = "http://localhost:9191/";
  constructor(private http: HttpClient) { }
  getAllCategory() {
    return this.http.get(this.uri + "category/");
  }
  addCategory(category: Category) {
    return this.http.post(this.uri + "category/add", category);
  }
  deleteCategory(id: any) {
    return this.http.delete(this.uri + "category/delete/" + id)
  }
  getAllProduit() {
    return this.http.get(this.uri + "produit/")
  }
  getById(id: any) {
    return this.http.get(this.uri + "category/" + id)
  }
  editCategory(category: Category) {
    return this.http.put(this.uri + "category/update/" + category.id, category);
  }
  addProduct(product: FormData) {
    console.log(product.get('produit'));

    return this.http.post(this.uri + "produit/add", product);
  }
  deleteProduit(id: any) {
    return this.http.get(this.uri + 'produit/delete/' + id)
  }
}
