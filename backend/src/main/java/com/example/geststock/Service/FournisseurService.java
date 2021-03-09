package com.example.geststock.Service;

import com.example.geststock.Model.FournisseurModel;
import com.example.geststock.Repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FournisseurService {
    @Autowired
    private FournisseurRepository fournisseurRepository;

    public List<FournisseurModel> getAll() {
        return fournisseurRepository.findAll();
    }

    public FournisseurModel getById(int id) {
        return fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));
    }

    public FournisseurModel add(FournisseurModel fournisseur) {
        if (fournisseurRepository.existsByEmail(fournisseur.getEmail())) {
            throw new RuntimeException("Un fournisseur avec cet email existe déjà");
        }
        return fournisseurRepository.save(fournisseur);
    }

    public FournisseurModel update(int id, FournisseurModel fournisseur) {
        FournisseurModel existingFournisseur = getById(id);
        existingFournisseur.setNom(fournisseur.getNom());
        existingFournisseur.setAdresse(fournisseur.getAdresse());
        existingFournisseur.setVille(fournisseur.getVille());
        existingFournisseur.setCode_postal(fournisseur.getCode_postal());
        existingFournisseur.setPays(fournisseur.getPays());
        existingFournisseur.setTelephone(fournisseur.getTelephone());
        existingFournisseur.setEmail(fournisseur.getEmail());
        existingFournisseur.setSite_web(fournisseur.getSite_web());
        existingFournisseur.setConditions_paiement(fournisseur.getConditions_paiement());
        return fournisseurRepository.save(existingFournisseur);
    }

    public void delete(int id) {
        fournisseurRepository.deleteById(id);
    }

    public List<FournisseurModel> findByNomContaining(String nom) {
        return fournisseurRepository.findByNomContaining(nom);
    }

    public List<FournisseurModel> findByVille(String ville) {
        return fournisseurRepository.findByVille(ville);
    }

    public FournisseurModel findByEmail(String email) {
        return fournisseurRepository.findByEmail(email);
    }
} 