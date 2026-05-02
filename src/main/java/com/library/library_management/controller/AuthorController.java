package com.library.library_management.controller;

import com.library.library_management.entity.Author;
import com.library.library_management.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    // List all authors
    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "authors/list";
    }

    // Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/add";
    }

    // Handle add form submission
    @PostMapping("/add")
    public String addAuthor(@ModelAttribute Author author,
                            RedirectAttributes redirectAttributes) {
        try {
            authorService.saveAuthor(author);
            redirectAttributes.addFlashAttribute("success", "Author added successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error: Duplicate or invalid data!");
        }
        return "redirect:/authors";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        authorService.getAuthorById(id).ifPresent(a -> model.addAttribute("author", a));
        return "authors/edit";
    }

    // Handle update
    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute Author author,
                               RedirectAttributes redirectAttributes) {
        authorService.updateAuthor(author);
        redirectAttributes.addFlashAttribute("success", "Author updated successfully!");
        return "redirect:/authors";
    }
}