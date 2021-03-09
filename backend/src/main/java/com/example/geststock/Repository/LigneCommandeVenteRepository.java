package com.example.geststock.Repository;

import com.example.geststock.Model.LigneCommandeVenteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LigneCommandeVenteRepository extends JpaRepository<LigneCommandeVenteModel, Integer> {
    List<LigneCommandeVenteModel> findByCommandeVente_Id(int commandeId);
    List<LigneCommandeVenteModel> findByProduit_Id(int produitId);
} 