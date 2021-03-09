package com.example.geststock.Repository;

import com.example.geststock.Model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Integer> {
    ClientModel findByEmail(String email);
    List<ClientModel> findByNomContaining(String nom);
    List<ClientModel> findByVille(String ville);
    boolean existsByEmail(String email);
    List<ClientModel> findByPays(String pays);
} 