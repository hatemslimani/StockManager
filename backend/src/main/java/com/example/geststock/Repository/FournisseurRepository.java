package com.example.geststock.Repository;

import com.example.geststock.Model.FournisseurModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FournisseurRepository extends JpaRepository<FournisseurModel, Integer> {
    FournisseurModel findByEmail(String email);
    List<FournisseurModel> findByNomContaining(String nom);
    List<FournisseurModel> findByVille(String ville);
    boolean existsByEmail(String email);
}
