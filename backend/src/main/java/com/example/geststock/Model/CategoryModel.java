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
@Table(name="category")
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String statut;
    
    @Column(updatable = false)
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp date_ajout;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private Timestamp date_modification;
    
    @JsonIgnore
    @OneToMany(
            mappedBy = "categoryModel",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true
    )
    private List<ProduitModel> produitModelList = new ArrayList<>();
}
