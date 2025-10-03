package com.rituraj.library_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rituraj.library_management.model.Member;
import com.rituraj.library_management.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


/*
 * MemberServiceImpl Class
 * Implements the MemberService interface to provide member-related operations
 */
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    
    // Injecting MemberRepository to interact with the database
    private final MemberRepository memberRepository;

    // Service methods implementation

    // Add a new member
    @Override
    public Member addMember(Member member){
        // Check if a member with the same email already exists
        if(memberRepository.existsByEmail(member.getEmail())){
            throw new IllegalArgumentException("Member already exists");
        }

        // Save the new member to the repository
        return memberRepository.save(member);
    }

    // Update an existing member
    @Override
    public Member updateMember(Long id, Member updatedMember){
        return memberRepository.findById(id).map(
                existingMember -> {
                    if(memberRepository.existsByEmail(updatedMember.getEmail()) && !existingMember.getEmail().equals(updatedMember.getEmail())){
                        throw new IllegalArgumentException("Member with email already exists " + updatedMember.getEmail());
                    }
                    existingMember.setEmail(updatedMember.getEmail());
                    existingMember.setName(updatedMember.getName());
                    existingMember.setMembershipDate(updatedMember.getMembershipDate());
                    existingMember.setActive(updatedMember.getActive());

                    return memberRepository.save(existingMember);
                }
            ).orElseThrow(() -> new IllegalArgumentException("No member found with id: " + id));
    }

    // Find a member by its ID
    @Override
    public Optional<Member> findMemberById(Long id){
        return memberRepository.findById(id);
    }

    // Find a member by its email
    @Override
    public Optional<Member> findMemberByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    // Get all members
    @Override
    public List<Member> findAllMembers(){
        return memberRepository.findAll();
    }

    // Delete a member by its ID
    @Override
    public void deleteMember(Long id){
        if(memberRepository.existsById(id)){
            memberRepository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException("Member does not exists");
        }       
    }
}
