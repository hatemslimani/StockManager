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
@Table(name="client")
public class ClientModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String ville;
    private String code_postal;
    private String pays;
    
    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp date_ajout;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private Timestamp date_modification;
} 