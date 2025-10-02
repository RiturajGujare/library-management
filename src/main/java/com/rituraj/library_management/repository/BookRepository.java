package com.rituraj.library_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rituraj.library_management.model.Book;

/*
 * BookRepository Interface
 * Provides CRUD operations and custom queries for Book entity
 */

@Repository  //repository annotation indicates that this interface is a Spring Data repository
public interface BookRepository extends JpaRepository<Book, Long>{
    
    // Custom query methods for Book entity

    // Find a book by its ISBN
    Optional<Book> findByISBN(String isbn);

    // Find books by author name
    List<Book> findByAuthor(String author);

    // Check if a book exists by its ISBN
    boolean existsByISBN(String isbn);

    // Find books with titles containing a specific keyword (case insensitive)
    List<Book> findByTitleContaining(String titlePart);

}
