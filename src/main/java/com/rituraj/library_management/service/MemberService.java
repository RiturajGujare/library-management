package com.rituraj.library_management.service;

import java.util.List;
import java.util.Optional;

import com.rituraj.library_management.model.Member;

/*
 * MemberService Interface
 * Defines the contract for member-related operations
 */
public interface MemberService {
    
    // Service methods for Member entity

    // Add a new member
    Member addMember(Member member);

    // Update an existing member
    Member updateMember(Long id, Member updatedMember);

    // Find a member by its ID
    Optional<Member> findMemberById(Long id);

    // Find a member by its email
    Optional<Member> findMemberByEmail(String email);

    // Get all members
    List<Member> findAllMembers();

    // Delete a member by its ID
    void deleteMember(Long id);

}
