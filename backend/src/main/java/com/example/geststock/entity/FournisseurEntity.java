package com.example.geststock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurEntity {
    private int id;
    private String nom;
    private String adresse;
    private String ville;
    private String code_postal;
    private String pays;
    private String telephone;
    private String email;
    private String site_web;
    private String conditions_paiement;
    private Timestamp date_ajout;
    private Timestamp date_modification;
} 