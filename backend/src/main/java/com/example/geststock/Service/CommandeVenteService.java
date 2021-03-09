package com.example.geststock.Service;

import com.example.geststock.Model.CommandeVenteModel;
import com.example.geststock.Model.LigneCommandeVenteModel;
import com.example.geststock.Model.ProduitModel;
import com.example.geststock.Model.StatutCommande;
import com.example.geststock.Repository.CommandeVenteRepository;
import com.example.geststock.Repository.LigneCommandeVenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommandeVenteService {
    @Autowired
    private CommandeVenteRepository commandeVenteRepository;
    
    @Autowired
    private LigneCommandeVenteRepository ligneCommandeVenteRepository;
    
    @Autowired
    private ProduitService produitService;

    public List<CommandeVenteModel> getAll() {
        return commandeVenteRepository.findAll();
    }

    public CommandeVenteModel getById(int id) {
        return commandeVenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande de vente non trouvée"));
    }

    @Transactional
    public CommandeVenteModel add(CommandeVenteModel commande) {
        commande.setReference("VNT" + System.currentTimeMillis());
        commande.setStatut(StatutCommande.EN_ATTENTE);
        double montantTotal = 0;
        for (LigneCommandeVenteModel ligne : commande.getLignes()) {
            ProduitModel produit = produitService.getById(ligne.getProduit().getId());
            ligne.setPrix_unitaire(produit.getPrix_unitaire());
            ligne.setCommandeVente(commande);
            montantTotal += ligne.getMontant();
        }
        commande.setMontant_total(montantTotal);
        
        return commandeVenteRepository.save(commande);
    }

    @Transactional
    public CommandeVenteModel update(int id, CommandeVenteModel commande) {
        CommandeVenteModel existingCommande = getById(id);
        
        existingCommande.setDate_livraison(commande.getDate_livraison());
        existingCommande.setStatut(commande.getStatut());
        if (commande.getLignes() != null) {
            existingCommande.getLignes().clear();
            double montantTotal = 0;
            for (LigneCommandeVenteModel ligne : commande.getLignes()) {
                ligne.setCommandeVente(existingCommande);
                existingCommande.getLignes().add(ligne);
                montantTotal += ligne.getMontant();
            }
            existingCommande.setMontant_total(montantTotal);
        }
        
        return commandeVenteRepository.save(existingCommande);
    }

    @Transactional
    public void delete(int id) {
        commandeVenteRepository.deleteById(id);
    }

    @Transactional
    public void validerCommande(int id) {
        CommandeVenteModel commande = getById(id);
        for (LigneCommandeVenteModel ligne : commande.getLignes()) {
            ProduitModel produit = ligne.getProduit();
            if (produit.getQuantite_stock() < ligne.getQuantite()) {
                throw new RuntimeException("Stock insuffisant pour le produit: " + produit.getNom());
            }
        }
        for (LigneCommandeVenteModel ligne : commande.getLignes()) {
            ProduitModel produit = ligne.getProduit();
            produit.setQuantite_stock(produit.getQuantite_stock() - ligne.getQuantite());
            produitService.update(produit.getId(), produit);
        }
        
        commande.setStatut(StatutCommande.VALIDEE);
        commandeVenteRepository.save(commande);
    }

    @Transactional
    public void livrerCommande(int id) {
        CommandeVenteModel commande = getById(id);
        commande.setStatut(StatutCommande.LIVREE);
        commandeVenteRepository.save(commande);
    }

    @Transactional
    public void annulerCommande(int id) {
        CommandeVenteModel commande = getById(id);
        commande.setStatut(StatutCommande.ANNULEE);
        commandeVenteRepository.save(commande);
    }

    public List<CommandeVenteModel> findByClient(int clientId) {
        return commandeVenteRepository.findByClient_Id(clientId);
    }

    public List<CommandeVenteModel> findByStatut(StatutCommande statut) {
        return commandeVenteRepository.findByStatut(statut);
    }
} 