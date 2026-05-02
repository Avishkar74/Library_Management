package com.library.library_management.controller;

import com.library.library_management.entity.Book;
import com.library.library_management.service.AuthorService;
import com.library.library_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired private BookService bookService;
    @Autowired private AuthorService authorService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getBooksWithAuthors()); // uses JOIN query
        return "books/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book,
                          @RequestParam Long authorId,
                          RedirectAttributes redirectAttributes) {
        try {
            authorService.getAuthorById(authorId).ifPresent(book::setAuthor);
            bookService.saveBook(book);
            redirectAttributes.addFlashAttribute("success", "Book added successfully!");
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "Error saving book: " + e.getMessage());
        }
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        bookService.getBookById(id).ifPresent(b -> model.addAttribute("book", b));
        model.addAttribute("authors", authorService.getAllAuthors());
        return "books/edit";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book,
                             @RequestParam Long authorId,
                             RedirectAttributes redirectAttributes) {
        authorService.getAuthorById(authorId).ifPresent(book::setAuthor);
        bookService.updateBook(book);
        redirectAttributes.addFlashAttribute("success", "Book updated successfully!");
        return "redirect:/books";
    }
}