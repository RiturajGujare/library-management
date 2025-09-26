package com.rituraj.library_management.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "issue_records")
@Getter  // This annotation generates getters for all fields
@Setter   // This annotation generates setters for all fields
@NoArgsConstructor
@AllArgsConstructor
@Builder

/*
 * IssueRecord Entity
 * Represents a record of a book issued to a member in the library management system
 */
public class IssueRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //book and member are mandatory fields for an issue record
    @ManyToOne(optional = false, fetch = FetchType.LAZY)  // Many issue records can be associated with one book ,fetch type LAZY to optimize performance
    @JoinColumn(name = "book_id", nullable = false)  // Specifies the foreign key column for the book relationship
    private Book book;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)  // Many issue records can be associated with one member, fetch type LAZY to optimize performance
    @JoinColumn(name = "member_id", nullable = false)  // Specifies the foreign key column for the member relationship
    private Member member;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    private LocalDate returnDate;   // Can be null if the book has not been returned yet

    // Status of the issue record, defaulting to ISSUED and uses EnumType.STRING to store enum names in the database
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IssueStatus status = IssueStatus.ISSUED ;

}
