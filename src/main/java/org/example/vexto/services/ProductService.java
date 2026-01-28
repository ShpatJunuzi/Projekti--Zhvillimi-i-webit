package org.example.vexto.services;

import org.example.vexto.entities.Product;
import org.example.vexto.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }


    public List<Product> search(String searchText, Long categoryId) {
        
        if ((searchText == null || searchText.isBlank()) && categoryId == null) {
            return repo.findAll();
        }

       
        if ((searchText == null || searchText.isBlank()) && categoryId != null) {
            return repo.findByCategoryId(categoryId);
        }

        
        if (searchText != null && !searchText.isBlank() && categoryId == null) {
            return repo.findByNameContainingIgnoreCase(searchText);
        }

        
        return repo.findByNameContainingIgnoreCaseAndCategoryId(searchText, categoryId);
    }

    public Product findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void save(Product product) {
        repo.save(product);
    }

    public void update(Long id, Product updatedProduct) {
        Product product = findById(id);
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setStock(updatedProduct.getStock());
            product.setCategory(updatedProduct.getCategory());
            repo.save(product);
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Object findAll(String searchText) {
        return search(searchText, null);
    }
}