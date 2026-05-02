package com.library.library_management.service;

import com.library.library_management.dto.BookAuthorDTO;
import com.library.library_management.entity.Book;
import com.library.library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public List<BookAuthorDTO> getBooksWithAuthors() {
        return bookRepository.findAllBooksWithAuthors();
    }
}