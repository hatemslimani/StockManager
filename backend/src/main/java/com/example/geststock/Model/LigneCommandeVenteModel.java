package com.example.geststock.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ligne_commande_vente")
public class LigneCommandeVenteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private CommandeVenteModel commandeVente;
    
    @ManyToOne
    @JoinColumn(name = "produit_id")
    private ProduitModel produit;
    
    private int quantite;
    private double prix_unitaire;
    private double montant;
    
    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Timestamp date_ajout;
    
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Timestamp date_modification;
    
    @PrePersist
    @PreUpdate
    public void calculateMontant() {
        this.montant = this.quantite * this.prix_unitaire;
    }
} 