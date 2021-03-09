package com.example.geststock.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "commande_vente")
public class CommandeVenteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String reference;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Timestamp date_commande;
    
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Timestamp date_livraison;
    
    @Enumerated(EnumType.STRING)
    private StatutCommande statut;
    
    private double montant_total;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;
    @JsonIgnore
    @OneToMany(mappedBy = "commandeVente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneCommandeVenteModel> lignes = new ArrayList<>();
    
    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Timestamp date_ajout;
    
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Timestamp date_modification;
}

