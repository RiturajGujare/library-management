package com.rituraj.library_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rituraj.library_management.model.Book;
import com.rituraj.library_management.repository.BookRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/*
 * BookServiceImpl Class
 * Implements the BookService interface to provide book-related operations
 */

 //service annotation indicates that this class is a service component in the Spring context
@Service
//transactional annotation indicates that methods in this class should be executed within a transaction
@Transactional
//requiredargsconstructor annotation generates a constructor with required arguments (final fields)
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    // Injecting BookRepository to interact with the database
    private final BookRepository bookRepository;

    // Service methods implementation

    // Add a new book
    @Override
    public Book addBook(Book book){

        // Check if a book with the same ISBN already exists
        if(bookRepository.existsByIsbn(book.getIsbn())){
            throw new IllegalArgumentException("Book with ISBN already exists " + book.getIsbn());
        }

        // Save the new book to the repository
        return bookRepository.save(book);
    }

    // Update an existing book
    @Override
    public Book updateBook(Long id, Book updatedBook){

        // Find the existing book by ID and update its details
        return bookRepository.findById(id).map(
            existingBook -> {
                if(bookRepository.existsByIsbn(updatedBook.getIsbn()) && !existingBook.getIsbn().equals(updatedBook.getIsbn())){
                    throw new IllegalArgumentException("Book with ISBN already exists " + updatedBook.getIsbn());
                }
                existingBook.setAuthor(updatedBook.getAuthor());
                existingBook.setTitle(updatedBook.getTitle());
                existingBook.setIsbn(updatedBook.getIsbn());
                existingBook.setPublishedDate(updatedBook.getPublishedDate());
                existingBook.setAvailableCopies(updatedBook.getAvailableCopies());
                existingBook.setTotalCopies(updatedBook.getTotalCopies());
                return bookRepository.save(existingBook);
            }
        ).orElseThrow(() -> new IllegalArgumentException("No Book with mentioned id " + id));
        
    }

    // Find a book by its ISBN
    @Override
    public Optional<Book> findBookByIsbn(String isbn){
        return bookRepository.findByIsbn(isbn);
    }


    // Find a book by its ID
    @Override
    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }


    // Get all books
    @Override
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    // Get books by author name
    @Override
    public List<Book> getBookByAuthor(String author){
        return bookRepository.findByAuthor(author);
    }
    

    // Get books with titles containing a specific string (case insensitive)
    @Override
    public List<Book> getBookByTitle(String title){
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }


    // Delete a book by its ID
    @Override
    public void deleteBook(Long id){

        // Check if the book exists before attempting to delete
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
        }

        else throw new IllegalArgumentException("No Book with mentioned id " + id);
    }
}
