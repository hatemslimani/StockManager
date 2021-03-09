package com.example.geststock.Service;

import com.example.geststock.Model.DetailCommandeAchatModel;
import com.example.geststock.Repository.DetailCommandeAchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailCommandeAchatService {
    @Autowired
    private DetailCommandeAchatRepository detailCommandeAchatRepository;

    public List<DetailCommandeAchatModel> getAll() {
        return detailCommandeAchatRepository.findAll();
    }

    public DetailCommandeAchatModel getById(int id) {
        return detailCommandeAchatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Détail de commande non trouvé"));
    }

    public DetailCommandeAchatModel add(DetailCommandeAchatModel detail) {
        return detailCommandeAchatRepository.save(detail);
    }

    public DetailCommandeAchatModel update(int id, DetailCommandeAchatModel detail) {
        DetailCommandeAchatModel existingDetail = getById(id);
        existingDetail.setPrix_achat(detail.getPrix_achat());
        existingDetail.setQuantite_achete(detail.getQuantite_achete());
        existingDetail.setProduitModel(detail.getProduitModel());
        existingDetail.setCommandeAchat(detail.getCommandeAchat());
        return detailCommandeAchatRepository.save(existingDetail);
    }

    public void delete(int id) {
        detailCommandeAchatRepository.deleteById(id);
    }

    public List<DetailCommandeAchatModel> findByCommandeId(int idCommande) {
        return detailCommandeAchatRepository.findByCommandeAchat_Id(idCommande);
    }

    public List<DetailCommandeAchatModel> findByProduitId(int idProduit) {
        return detailCommandeAchatRepository.findByProduitModel_Id(idProduit);
    }
} 