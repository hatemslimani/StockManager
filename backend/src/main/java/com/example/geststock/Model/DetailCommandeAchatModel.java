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
@Table(name="detail_commande_achat")
public class DetailCommandeAchatModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double prix_achat;
	private int quantite_achete;
	
	@ManyToOne
	@JoinColumn(name = "id_produit")
	private ProduitModel produitModel;
	
	@ManyToOne
	@JoinColumn(name = "id_commande_achat")
	private CommandeAchatModel commandeAchat;
	
	@Column(updatable = false)
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp date_ajout;
	
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp date_modification;
}
