package com.example.geststock.Service;

import com.example.geststock.Model.ProduitModel;
import com.example.geststock.Repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {
    @Autowired
    private ProduitRepository produitRepository;

    public List<ProduitModel> getAll() {
        return produitRepository.findAll();
    }

    public ProduitModel getById(int id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
    }

    public ProduitModel add(ProduitModel produit) {
        if (produitRepository.existsByCode(produit.getCode())) {
            throw new RuntimeException("Un produit avec ce code existe déjà");
        }
        return produitRepository.save(produit);
    }

    public ProduitModel update(int id, ProduitModel produit) {
        ProduitModel existingProduit = getById(id);
        existingProduit.setCode(produit.getCode());
        existingProduit.setNom(produit.getNom());
        existingProduit.setDescription(produit.getDescription());
        existingProduit.setPrix_unitaire(produit.getPrix_unitaire());
        existingProduit.setPrix_achat(produit.getPrix_achat());
        existingProduit.setQuantite_stock(produit.getQuantite_stock());
        existingProduit.setCode_barres(produit.getCode_barres());
        existingProduit.setPoids(produit.getPoids());
        existingProduit.setUnite_mesure(produit.getUnite_mesure());
        existingProduit.setStatut(produit.getStatut());
        existingProduit.setMarque(produit.getMarque());
        existingProduit.setDate_expiration(produit.getDate_expiration());
        existingProduit.setOrigine(produit.getOrigine());
        existingProduit.setDimensions(produit.getDimensions());
        existingProduit.setFournisseur(produit.getFournisseur());
        existingProduit.setCategoryModel(produit.getCategoryModel());
        return produitRepository.save(existingProduit);
    }

    public void delete(int id) {
        produitRepository.deleteById(id);
    }

    public List<ProduitModel> findByNomContaining(String nom) {
        return produitRepository.findByNomContaining(nom);
    }

    public List<ProduitModel> findByFournisseur(int idFournisseur) {
        return produitRepository.findByFournisseur_Id(idFournisseur);
    }

    public List<ProduitModel> findByCategorie(int idCategorie) {
        return produitRepository.findByCategoryModel_Id(idCategorie);
    }
}
