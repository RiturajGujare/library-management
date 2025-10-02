package com.rituraj.library_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rituraj.library_management.model.Member;

/*
 * MemberRepository Interface
 * Provides CRUD operations and custom queries for Member entity
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Custom query methods for Member entity by email.
    Optional<Member> findByEmail(String email);

    // Check if a member exists by email
    boolean existsByEmail(String email);

    // Find members by name containing a specific string (case insensitive)
    List<Member> findByNameContainingIgnoreCase(String name);
}
