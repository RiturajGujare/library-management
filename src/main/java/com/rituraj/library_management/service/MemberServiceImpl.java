package com.rituraj.library_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rituraj.library_management.model.Member;
import com.rituraj.library_management.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    
    private final MemberRepository memberRepository;

    @Override
    public Member addMember(Member member){
        if(memberRepository.existsByEmail(member.getEmail())){
            throw new IllegalArgumentException("Member already exists");
        }

        return memberRepository.save(member);
    }

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

    @Override
    public Optional<Member> findMemberById(Long id){
        return memberRepository.findById(id);
    }

    @Override
    public Optional<Member> findMemberByEmail(String email){
        return memberRepository.findByEmail(email);
    }

    @Override
    public List<Member> findAllMembers(){
        return memberRepository.findAll();
    }

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
