package com.library.library_management.repository;

import com.library.library_management.entity.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.BeforeEach;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void resetDatabase() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    void testSaveAndFindAuthor() {
        Author author = new Author(null, "Test Author", "Indian", "test@email.com", null);
        Author saved = authorRepository.save(author);

        assertNotNull(saved.getId());
        assertEquals("Test Author", saved.getName());
    }

    @Test
    void testFindAll() {
        authorRepository.save(new Author(null, "Author A", "British", "a@test.com", null));
        authorRepository.save(new Author(null, "Author B", "American", "b@test.com", null));

        List<Author> authors = authorRepository.findAll();
        assertEquals(2, authors.size());
    }
}