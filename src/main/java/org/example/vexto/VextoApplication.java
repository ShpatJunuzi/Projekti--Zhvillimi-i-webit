package org.example.vexto;

import org.example.vexto.entities.Category;
import org.example.vexto.entities.Product;
import org.example.vexto.repositories.CategoryRepository;
import org.example.vexto.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VextoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VextoApplication.class, args);
    }

    @Bean
    CommandLineRunner seed(CategoryRepository catRepo,
                           ProductRepository prodRepo) {
        return args -> {
            if (catRepo.count() == 0) {
                Category c1 = new Category();
                c1.setName("Clothes");

                Category c2 = new Category();
                c2.setName("Shoes");

                catRepo.save(c1);
                catRepo.save(c2);

                Product p1 = new Product();
                p1.setName("Puma Hoodie");
                p1.setPrice(29.99);
                p1.setCategory(c1);

                Product p2 = new Product();
                p2.setName("Nike Air");
                p2.setPrice(89.99);
                p2.setCategory(c2);

                prodRepo.save(p1);
                prodRepo.save(p2);
            }
        };
    }

}
