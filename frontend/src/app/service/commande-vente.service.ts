import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommandeVente, LigneCommandeVente } from '../model/commande-vente.model';

@Injectable({
    providedIn: 'root'
})
export class CommandeVenteService {
    private uri: string = "http://localhost:9191/";

    constructor(private http: HttpClient) { }

    getAll(): Observable<CommandeVente[]> {
        return this.http.get<CommandeVente[]>(this.uri + "ventes/");
    }

    getById(id: number): Observable<CommandeVente> {
        return this.http.get<CommandeVente>(this.uri + "ventes/" + id);
    }

    getLignesCommande(id: number): Observable<LigneCommandeVente[]> {
        return this.http.get<LigneCommandeVente[]>(this.uri + "ventes/" + id + "/lignes");
    }

    create(commande: CommandeVente): Observable<CommandeVente> {
        return this.http.post<CommandeVente>(this.uri + "ventes/add", commande);
    }

    update(commande: CommandeVente): Observable<CommandeVente> {
        return this.http.put<CommandeVente>(this.uri + "ventes/update/" + commande.id, commande);
    }

    delete(id: number): Observable<any> {
        return this.http.delete(this.uri + "ventes/delete/" + id);
    }

    updateStatus(id: number, statut: string): Observable<any> {
        return this.http.put(this.uri + "ventes/" + id + "/status", { statut });
    }

    addLigneCommande(ligne: LigneCommandeVente): Observable<LigneCommandeVente> {
        return this.http.post<LigneCommandeVente>(this.uri + "ventes/ligne/add", ligne);
    }

    updateLigneCommande(ligne: LigneCommandeVente): Observable<LigneCommandeVente> {
        return this.http.put<LigneCommandeVente>(this.uri + "ventes/ligne/update/" + ligne.id, ligne);
    }

    deleteLigneCommande(id: number): Observable<any> {
        return this.http.delete(this.uri + "ventes/ligne/delete/" + id);
    }
} 