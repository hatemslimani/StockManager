package com.example.geststock.Repository;

import com.example.geststock.Model.CommandeAchatModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface CommandeAchatRepository extends JpaRepository<CommandeAchatModel, Integer> {
    CommandeAchatModel findByCode(String code);
}
