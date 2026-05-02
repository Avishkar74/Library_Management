package com.library.library_management.config;

import com.library.library_management.entity.Author;
import com.library.library_management.entity.Book;
import com.library.library_management.repository.AuthorRepository;
import com.library.library_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired private AuthorRepository authorRepository;
    @Autowired private BookRepository bookRepository;

    @Override
    public void run(String... args) {
        if (authorRepository.count() > 0) return; // Don't seed twice

        Author a1 = authorRepository.save(new Author(null, "J.K. Rowling", "British", "jk@email.com", null));
        Author a2 = authorRepository.save(new Author(null, "George Orwell", "British", "orwell@email.com", null));
        Author a3 = authorRepository.save(new Author(null, "Mark Twain", "American", "twain@email.com", null));
        Author a4 = authorRepository.save(new Author(null, "Jane Austen", "British", "austen@email.com", null));
        Author a5 = authorRepository.save(new Author(null, "Leo Tolstoy", "Russian", "tolstoy@email.com", null));
        Author a6 = authorRepository.save(new Author(null, "Fyodor Dostoevsky", "Russian", "dostoevsky@email.com", null));
        Author a7 = authorRepository.save(new Author(null, "Ernest Hemingway", "American", "hem@email.com", null));
        Author a8 = authorRepository.save(new Author(null, "Franz Kafka", "Czech", "kafka@email.com", null));
        Author a9 = authorRepository.save(new Author(null, "Gabriel Garcia Marquez", "Colombian", "gg@email.com", null));
        authorRepository.save(new Author(null, "Haruki Murakami", "Japanese", "hm@email.com", null));

        bookRepository.saveAll(List.of(
                new Book(null, "Harry Potter", "Fantasy", 1997, 499.0, a1),
                new Book(null, "1984", "Dystopian", 1949, 299.0, a2),
                new Book(null, "Animal Farm", "Satire", 1945, 199.0, a2),
                new Book(null, "Adventures of Tom Sawyer", "Adventure", 1876, 249.0, a3),
                new Book(null, "Pride and Prejudice", "Romance", 1813, 349.0, a4),
                new Book(null, "War and Peace", "Historical", 1869, 599.0, a5),
                new Book(null, "Crime and Punishment", "Thriller", 1866, 399.0, a6),
                new Book(null, "The Old Man and the Sea", "Fiction", 1952, 279.0, a7),
                new Book(null, "The Metamorphosis", "Fiction", 1915, 199.0, a8),
                new Book(null, "100 Years of Solitude", "Fantasy", 1967, 449.0, a9)
        ));
    }
}