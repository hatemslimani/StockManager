package com.example.geststock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeVenteDTO {
    private String produit;
    private Integer quantite;
    private Double prix;
    private Double montant;
} 