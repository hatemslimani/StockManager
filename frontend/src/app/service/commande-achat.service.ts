import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommandeAchatService {
  private uri: String = "http://localhost:9191/";
  constructor(private http: HttpClient, private toastr: ToastrService) { }
  getAll() {
    return this.http.get(this.uri + "commandes-achat/");
  }
  addCommandeAchat(f: any) {
    return this.http.post(this.uri + "commandes-achat/add", f);
  }
  delete(id: any) {
    return this.http.get(this.uri + "commandes-achat/delete/" + id);
  }
  getById(id: any) {
    return this.http.get(this.uri + "commandes-achat/" + id)
  }
  edit(id: any, fournisseur: any) {
    return this.http.post(this.uri + "fournisseur/edit/" + id, fournisseur);
  }
  accepterCommande(id: any) {
    return this.http.get(this.uri + "commandes-achat/accepteCommande/" + id)
  }
  refuserCommande(id: any) {
    return this.http.get(this.uri + "commandes-achat/refuserCommande/" + id)
  }
  downloadPDF(id: number): Observable<Blob> {
    const headers = new HttpHeaders({
      'Accept': 'application/pdf'
    });
    return this.http.get(this.uri + `commandes-achat/download/${id}`, {
      headers: headers,
      responseType: 'blob'
    });
  }
}
