package com.example.geststock.Service;

import com.example.geststock.Model.CommandeAchatModel;
import com.example.geststock.Model.DetailCommandeAchatModel;
import com.example.geststock.Model.ProduitModel;
import com.example.geststock.Repository.CommandeAchatRepository;
import com.example.geststock.Repository.DetailCommandeAchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CommandeAchatService {
    @Autowired
    private CommandeAchatRepository commandeAchatRepository;
    
    @Autowired
    private DetailCommandeAchatRepository detailCommandeAchatRepository;
    
    @Autowired
    private ProduitService produitService;

    public List<CommandeAchatModel> getAll() {
        return commandeAchatRepository.findAll();
    }

    public CommandeAchatModel getById(int id) {
        return commandeAchatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande d'achat non trouvée"));
    }

    @Transactional
    public CommandeAchatModel add(CommandeAchatModel commande) {
        commande.setCode("CMD" + System.currentTimeMillis());
        double totalCommande = 0;
        for (DetailCommandeAchatModel detail : commande.getDetailList()) {
            totalCommande += detail.getPrix_achat() * detail.getQuantite_achete();
        }
        commande.setTotal_commande(totalCommande);
        CommandeAchatModel savedCommande = commandeAchatRepository.save(commande);
        for (DetailCommandeAchatModel detail : commande.getDetailList()) {
            detail.setCommandeAchat(savedCommande);
            detailCommandeAchatRepository.save(detail);
        }
        
        return savedCommande;
    }

    @Transactional
    public CommandeAchatModel update(int id, CommandeAchatModel commande) {
        CommandeAchatModel existingCommande = getById(id);
        existingCommande.setStatut(commande.getStatut());
        existingCommande.setFournisseurModel(commande.getFournisseurModel());
        if (commande.getDetailList() != null) {
            double totalCommande = 0;
            for (DetailCommandeAchatModel detail : commande.getDetailList()) {
                totalCommande += detail.getPrix_achat() * detail.getQuantite_achete();
            }
            existingCommande.setTotal_commande(totalCommande);
        }
        
        return commandeAchatRepository.save(existingCommande);
    }

    @Transactional
    public void delete(int id) {
        commandeAchatRepository.deleteById(id);
    }
     @Transactional
    public void validerCommande(int id) {
        CommandeAchatModel commande = getById(id);
        commande.setStatut("VALIDEE");

        for (DetailCommandeAchatModel detail : commande.getDetailList()) {
            ProduitModel produit = detail.getProduitModel();
            produit.setQuantite_stock(produit.getQuantite_stock() + detail.getQuantite_achete());
            produitService.update(produit.getId(), produit);
        }
        
        commandeAchatRepository.save(commande);
    }

    @Transactional
    public void refuserCommande(int id) {
        CommandeAchatModel commande = getById(id);
        commande.setStatut("REFUSEE");
        commandeAchatRepository.save(commande);
    }
} 