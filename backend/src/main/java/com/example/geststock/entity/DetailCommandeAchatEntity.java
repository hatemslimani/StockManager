package com.example.geststock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailCommandeAchatEntity {
    private int id;
    private double prix_achat;
    private int quantite_achete;
    private int id_commande_achat;
    private String code_commande;
    private int id_produit;
    private String nom_produit;
    private Timestamp date_ajout;
    private Timestamp date_modification;
} 