package com.rituraj.library_management.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity   // This annotation specifies that the class is an entity and is mapped to a database table
@Table(name = "books")    // This annotation specifies the name of the database table to be used for mapping
@Data  // This annotation generates getters, setters, toString, equals, and hashCode methods
@NoArgsConstructor  // This annotation generates a no-argument constructor
@AllArgsConstructor   // This annotation generates a constructor with 1 parameter for each field in the class
@Builder   // This annotation produces complex builder APIs for your classes


/*
 * Book Entity
 * Represents a book in the library management system
 */
public class Book {
  
    @Id   // This annotation specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // This annotation provides for the specification of generation strategies for the values of primary keys
    private Long id;  

    @Column(nullable = false)  // This annotation is used to specify the mapped column for a persistent property or field
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique = true)  // unique = true ensures that the ISBN is unique across all book records
    private String isbn;

    // publishedDate can be null if the publication date is unknown
    private LocalDate publishedDate;

    @Column(nullable = false)
    private Integer totalCopies = 0;  // totalCopies can never be null, defaulting to 0

    @Column(nullable = false)
    private Integer availableCopies = 0;   // availableCopies can never be null, defaulting to 0


}
