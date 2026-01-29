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


    public List<Product> search(String searchText, Long categoryId) {
        return repo.searchProducts(searchText, categoryId);
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
            product.setCategory(updatedProduct.getCategory());
            if (updatedProduct.getImage() != null) {
                product.setImage(updatedProduct.getImage());
            }
            repo.save(product);
        }
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }


    public List<Product> findAll() {
        return repo.findAll();
    }
}