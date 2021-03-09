package com.example.geststock.Repository;

import com.example.geststock.Model.DetailCommandeAchatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DetailCommandeAchatRepository extends JpaRepository<DetailCommandeAchatModel, Integer> {
    List<DetailCommandeAchatModel> findByCommandeAchat_Id(int idCommande);
    List<DetailCommandeAchatModel> findByProduitModel_Id(int idProduit);
}
