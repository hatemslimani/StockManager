package com.example.geststock.Service;

import com.example.geststock.Model.CategoryModel;
import com.example.geststock.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CategoryModel> getAll() {
        return categoryRepository.findAll();
    }

    public CategoryModel getById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
    }

    public CategoryModel add(CategoryModel category) {
        if (categoryRepository.existsByNom(category.getNom())) {
            throw new RuntimeException("Une catégorie avec ce nom existe déjà");
        }
        return categoryRepository.save(category);
    }

    public CategoryModel update(int id, CategoryModel category) {
        CategoryModel existingCategory = getById(id);
        existingCategory.setNom(category.getNom());
        existingCategory.setStatut(category.getStatut());
        return categoryRepository.save(existingCategory);
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    public CategoryModel findByNom(String nom) {
        return categoryRepository.findByNom(nom);
    }
}
