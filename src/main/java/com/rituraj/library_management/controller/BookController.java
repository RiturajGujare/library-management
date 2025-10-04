package com.rituraj.library_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rituraj.library_management.model.Book;
import com.rituraj.library_management.service.BookService;

import lombok.RequiredArgsConstructor;

/*
    * BookController Class
    * Handles HTTP requests related to Book entity
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books") // Base URL for all book-related endpoints
public class BookController {

    // Injecting BookService to handle business logic
    private final BookService bookService;

    // Controller methods to handle HTTP requests

    // Add a new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.addBook(book));
    }
    
    // Update an existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook){
        return ResponseEntity.ok(bookService.updateBook(id, updatedBook));
    }

    // Get a book by its ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        return bookService.findBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Delete a book by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // Get a book by its ISBN
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByISBN(@PathVariable String isbn){
        return bookService.findBookByISBN(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get books with author name
    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String author){
        return ResponseEntity.ok(bookService.getBookByAuthor(author)); 
                
    }

    //Get books with title containing a specific string (case insensitive)
    @GetMapping("/title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable String title){
        return ResponseEntity.ok(bookService.getBookByTitle(title));
    }
}
