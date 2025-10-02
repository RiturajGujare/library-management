package com.rituraj.library_management.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rituraj.library_management.model.Book;
import com.rituraj.library_management.model.IssueRecord;
import com.rituraj.library_management.model.IssueStatus;
import com.rituraj.library_management.model.Member;

/*
 * IssueRecordRepository Interface
 * Provides CRUD operations and custom queries for IssueRecord entity
 */
@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long>{

    // Custom query methods for IssueRecord entity

    // Find issue records by member
    List<IssueRecord> findByMember(Member member);

    // Find issue records by book
    List<IssueRecord> findByBook(Book book);

    // Find issue record for a book by status (e.g., currently issued)
    Optional<IssueRecord> findByBookAndStatus(Book book, IssueStatus status);

    // Find issue records by member and status
    List<IssueRecord> findByMemberAndStatus(Member member, IssueStatus status);

    // Find all overdue records
    List<IssueRecord> findByDueDateBeforeAndStatus(LocalDate date, IssueStatus status);
    
    // Check if an active issue record exists for a given book and member
    boolean existsByBookAndMemberAndStatus(Book book, Member member, IssueStatus status);
    
}
