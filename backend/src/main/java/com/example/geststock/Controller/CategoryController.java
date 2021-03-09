package com.example.geststock.Controller;

import com.example.geststock.Model.CategoryModel;
import com.example.geststock.Service.CategoryService;
import com.example.geststock.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public List<CategoryEntity> getAll() {
        return categoryService.getAll().stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryEntity> getById(@PathVariable int id) {
        CategoryModel category = categoryService.getById(id);
        return ResponseEntity.ok(convertToEntity(category));
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryEntity> add(@RequestBody CategoryEntity categoryEntity) {
        CategoryModel category = convertToModel(categoryEntity);
        CategoryModel savedCategory = categoryService.add(category);
        return ResponseEntity.ok(convertToEntity(savedCategory));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryEntity> update(@PathVariable int id, @RequestBody CategoryEntity categoryEntity) {
        CategoryModel category = convertToModel(categoryEntity);
        CategoryModel updatedCategory = categoryService.update(id, category);
        return ResponseEntity.ok(convertToEntity(updatedCategory));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    private CategoryEntity convertToEntity(CategoryModel model) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(model.getId());
        entity.setNom(model.getNom());
        entity.setStatut(model.getStatut());
        entity.setDate_ajout(model.getDate_ajout());
        entity.setDate_modification(model.getDate_modification());
        return entity;
    }

    private CategoryModel convertToModel(CategoryEntity entity) {
        CategoryModel model = new CategoryModel();
        model.setId(entity.getId());
        model.setNom(entity.getNom());
        model.setStatut(entity.getStatut());
        return model;
    }
}
