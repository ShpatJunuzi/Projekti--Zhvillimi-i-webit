package org.example.vexto.repositories;

import org.example.vexto.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Kërkon sipas emrit (që përmban tekstin) DHE sipas ID-së së kategorisë
    List<Product> findByNameContainingIgnoreCaseAndCategoryId(String name, Long categoryId);

    // Kërkon vetëm sipas emrit (nëse kategoria nuk është zgjedhur)
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryId(Long categoryId);
}