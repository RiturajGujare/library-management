package com.rituraj.library_management.service;

import java.util.List;
import java.util.Optional;

import com.rituraj.library_management.model.Book;

/*
 * BookService Interface
 * Defines the contract for book-related operations
 */
public interface BookService  {
    
    // Service methods for Book entity

    // Add a new book
    Book addBook(Book book);

    // Update an existing book
    Book updateBook(Long id, Book updatedBook);

    // Find a book by its ISBN
    Optional<Book> findBookByIsbn(String isbn);

    // Find a book by its ID
    Optional<Book> findBookById(Long id);

    // Get all books
    List<Book> getAllBooks();

    // Get books by author name
    List<Book> getBookByAuthor(String author);

    // Get books with titles containing a specific string (case insensitive)
    List<Book> getBookByTitle(String title);

    // Delete a book by its ID
    void deleteBook(Long id);
}
