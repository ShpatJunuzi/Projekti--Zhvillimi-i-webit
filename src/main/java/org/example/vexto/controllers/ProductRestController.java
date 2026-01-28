package org.example.vexto.controllers;

import org.example.vexto.entities.Product;
import org.example.vexto.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @Autowired
    private ProductService productService;


    @GetMapping("/check-stock/{id}")
    public ResponseEntity<Integer> getStock(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok(product.getQuantity());
    }
}