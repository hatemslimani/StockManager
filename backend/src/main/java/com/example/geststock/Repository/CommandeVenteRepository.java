package com.example.geststock.Repository;

import com.example.geststock.Model.CommandeVenteModel;
import com.example.geststock.Model.StatutCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeVenteRepository extends JpaRepository<CommandeVenteModel, Integer> {
    CommandeVenteModel findByReference(String reference);
    List<CommandeVenteModel> findByClient_Id(int clientId);
    List<CommandeVenteModel> findByStatut(StatutCommande statut);
} 