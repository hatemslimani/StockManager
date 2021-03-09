package com.example.geststock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    private int id;
    private String nom;
    private String statut;
    private Timestamp date_ajout;
    private Timestamp date_modification;
} 