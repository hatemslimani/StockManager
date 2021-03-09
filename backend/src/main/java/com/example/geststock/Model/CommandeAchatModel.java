package com.example.geststock.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="commande_achat")
public class CommandeAchatModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String code;
	private double total_commande;
	private String statut;
	
	@ManyToOne
	@JoinColumn(name = "id_fournisseur")
	private FournisseurModel fournisseurModel;
	
	@OneToMany(
		mappedBy = "commandeAchat",
		cascade = CascadeType.ALL,
		orphanRemoval = true
	)
	private List<DetailCommandeAchatModel> detailList = new ArrayList<>();
	
	@Column(name = "date_ajout", updatable = false)
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp date_ajout;
	
	@Column(name = "date_modification")
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Timestamp date_modification;
}
