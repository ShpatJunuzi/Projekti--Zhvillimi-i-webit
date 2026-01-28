package org.example.vexto.controllers;

import org.example.vexto.entities.Product;
import org.example.vexto.services.ProductService;
import org.example.vexto.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/app/products")
public class ProductController {

    private final ProductService service;
    private final CategoryService categoryService;

    public ProductController(ProductService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }


    @GetMapping
    public String products(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false) Long categoryId,
            Model model
    ) {

        model.addAttribute("products", service.search(searchText, categoryId));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("searchText", searchText);
        return "product/index";
    }


    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "product/add";
    }

    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute Product product,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "product/add";
        }
        service.save(product);
        return "redirect:/app/products";
    }


    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model) {
        var product = service.findById(id);
        if (product == null) {
            return "redirect:/app/products";
        }
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());
        return "product/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(
            @PathVariable Long id,
            @Valid @ModelAttribute Product product,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "product/edit";
        }
        service.update(id, product);
        return "redirect:/app/products";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/app/products";
    }
}