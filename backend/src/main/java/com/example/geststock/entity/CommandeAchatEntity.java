package com.example.geststock.entity;

import com.example.geststock.Model.ProduitModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeAchatEntity {
	private int id;
	private String code;
	private double total_commande;
	private String statut;
	private int id_fournisseur;
	private String nomFournisseur;
	private Timestamp date_ajout;
	private Timestamp date_modification;
	private List<ProduitModel> produits;
}
