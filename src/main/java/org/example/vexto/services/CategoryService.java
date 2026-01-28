package org.example.vexto.services;

import org.example.vexto.entities.Category;
import org.example.vexto.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Category save(Category category) {
        return repo.save(category);
    }
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
