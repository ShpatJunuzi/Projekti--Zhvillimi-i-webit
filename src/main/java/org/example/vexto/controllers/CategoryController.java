package org.example.vexto.controllers;

import org.example.vexto.entities.Category;
import org.example.vexto.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public String list(Model model) {
        model.addAttribute("categories", service.findAll());
        return "categories/index";
    }

    @GetMapping("/categories/add")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/add";
    }

    @PostMapping("/categories/add")
    public String save(Category category) {
        service.save(category);
        return "redirect:/categories";
    }
    @PostMapping("/categories/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/categories";
    }

}
