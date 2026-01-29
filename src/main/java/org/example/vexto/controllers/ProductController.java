package org.example.vexto.controllers;

import org.example.vexto.entities.Product;
import org.example.vexto.entities.Category;
import org.example.vexto.services.ProductService;
import org.example.vexto.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/app/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }


    @GetMapping
    public String products(
            @RequestParam(value = "searchText", required = false, defaultValue = "") String searchText,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            Model model
    ) {
        model.addAttribute("products", productService.search(searchText, categoryId));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("searchText", searchText);
        model.addAttribute("categoryId", categoryId);

        return "product/index";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/add";
    }


    @PostMapping("/add")
    public String saveProduct(
            @ModelAttribute("product") Product product,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile
    ) throws IOException {

        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = imageFile.getOriginalFilename();
            product.setImage(fileName);

            String uploadDir = "src/main/resources/static/images/";
            java.nio.file.Path uploadPath = java.nio.file.Paths.get(uploadDir);
            if (!java.nio.file.Files.exists(uploadPath)) {
                java.nio.file.Files.createDirectories(uploadPath);
            }
            try (java.io.InputStream inputStream = imageFile.getInputStream()) {
                java.nio.file.Path filePath = uploadPath.resolve(fileName);
                java.nio.file.Files.copy(inputStream, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }
        }

        productService.save(product);
        return "redirect:/app/products";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/app/products";
        }

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                                @ModelAttribute("product") Product product,
                                @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {

        product.setId(id);


        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = imageFile.getOriginalFilename();
            product.setImage(fileName);

            String uploadDir = "src/main/resources/static/images/";
            java.nio.file.Path uploadPath = java.nio.file.Paths.get(uploadDir);


            if (!java.nio.file.Files.exists(uploadPath)) {
                java.nio.file.Files.createDirectories(uploadPath);
            }

            try (java.io.InputStream inputStream = imageFile.getInputStream()) {
                java.nio.file.Path filePath = uploadPath.resolve(fileName);
                java.nio.file.Files.copy(inputStream, filePath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            }
        }

        productService.save(product);
        return "redirect:/app/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/app/products";
    }
}

