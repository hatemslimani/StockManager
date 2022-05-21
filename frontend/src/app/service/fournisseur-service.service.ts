import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FournisseurServiceService {
  private uri:String="http://localhost:9191/";
  constructor(private http:HttpClient) { }
  getAll()
  {
    return this.http.get(this.uri+"fournisseur/");
  }
  addFournisseur(f:any){
    return this.http.post(this.uri+"fournisseur/add",f);
  }
  delete(id:any){
    return this.http.get(this.uri+"fournisseur/delete/"+id);
  }
  getById(id:any){
    return this.http.get(this.uri+"fournisseur/getById/"+id)
  }
  edit(id:any,fournisseur:any){
    return this.http.post(this.uri+"fournisseur/edit/"+id,fournisseur);
  }
}
