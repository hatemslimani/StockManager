package com.example.geststock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitEntity {
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
    private Date date_expiration;
    private String origine;
    private String dimensions;
    private int id_fournisseur;
    private String nom_fournisseur;
    private int id_categorie;
    private String nom_categorie;
    private Timestamp date_ajout;
    private Timestamp date_modification;
}
