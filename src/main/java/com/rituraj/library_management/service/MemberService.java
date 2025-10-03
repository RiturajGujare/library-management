package com.rituraj.library_management.service;

import java.util.List;
import java.util.Optional;

import com.rituraj.library_management.model.Member;

public interface MemberService {
    
    Member addMember(Member member);

    Member updateMember(Long id, Member updatedMember);

    Optional<Member> findMemberById(Long id);

    Optional<Member> findMemberByEmail(String email);

    List<Member> findAllMembers();

    void deleteMember(Long id);


}
