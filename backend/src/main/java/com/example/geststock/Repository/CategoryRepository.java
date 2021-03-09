package com.example.geststock.Repository;

import com.example.geststock.Model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {
    CategoryModel findByNom(String nom);
    boolean existsByNom(String nom);
}
