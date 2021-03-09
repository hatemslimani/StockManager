package com.example.geststock.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="produit")
public class ProduitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String nom;
    private String description;
    private double prix_unitaire;
    private double prix_achat;
    private int quantite_stock;
    private String code_barres;
    private double poids;
    private String unite_mesure;
    private String statut;
    private String marque;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date_expiration;
    private String image;
    private String origine;
    private String dimensions;
    
    @ManyToOne
    @JoinColumn(name = "id_fournisseur")
    private FournisseurModel fournisseur;
    
    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private CategoryModel categoryModel;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private Timestamp date_ajout;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private Timestamp date_modification;
}
