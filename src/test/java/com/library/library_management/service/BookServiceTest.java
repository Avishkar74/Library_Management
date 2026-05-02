package com.library.library_management.service;

import com.library.library_management.entity.Author;
import com.library.library_management.entity.Book;
import com.library.library_management.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testGetAllBooks() {
        Book book = new Book(1L, "1984", "Dystopian", 1949, 299.0, new Author());
        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<Book> result = bookService.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("1984", result.get(0).getTitle());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testSaveBook() {
        Book book = new Book(null, "New Book", "Fiction", 2020, 199.0, new Author());
        when(bookRepository.save(book)).thenReturn(book);

        Book saved = bookService.saveBook(book);

        assertNotNull(saved);
        assertEquals("New Book", saved.getTitle());
    }

    @Test
    void testGetBookById_Found() {
        Book book = new Book(1L, "1984", "Dystopian", 1949, 299.0, new Author());
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.getBookById(1L);

        assertTrue(result.isPresent());
        assertEquals("1984", result.get().getTitle());
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());
        Optional<Book> result = bookService.getBookById(99L);
        assertFalse(result.isPresent());
    }
}