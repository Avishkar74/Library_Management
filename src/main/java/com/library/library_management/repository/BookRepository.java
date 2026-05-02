package com.library.library_management.repository;

import com.library.library_management.dto.BookAuthorDTO;
import com.library.library_management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    // Custom INNER JOIN query (required by assignment)
    @Query("SELECT new com.library.library_management.dto.BookAuthorDTO(" +
            "b.id, b.title, b.genre, b.publishedYear, b.price, a.name, a.nationality) " +
            "FROM Book b INNER JOIN b.author a")
    List<BookAuthorDTO> findAllBooksWithAuthors();
}