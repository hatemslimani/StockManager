package com.example.geststock.Repository;

import com.example.geststock.Model.ProduitModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<ProduitModel, Integer> {
    ProduitModel findByCode(String code);
    List<ProduitModel> findByNomContaining(String nom);
    List<ProduitModel> findByFournisseur_Id(int idFournisseur);
    List<ProduitModel> findByCategoryModel_Id(int idCategorie);
    boolean existsByCode(String code);
}
