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

import com.rituraj.library_management.model.Member;
import com.rituraj.library_management.service.MemberService;

import lombok.RequiredArgsConstructor;

/*
 * MemberController Class
 * Handles HTTP requests related to Member entity
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members") // Base URL for all member-related endpoints
public class MemberController {

    // Injecting MemberService to handle business logic
    private final MemberService memberService;

    // Controller methods to handle HTTP requests

    // Add a new member
    @PostMapping
    public ResponseEntity<Member> addMember(@RequestBody Member member){
        return ResponseEntity.ok(memberService.addMember(member));
    }

    // Get all members
    @GetMapping
    public ResponseEntity<List<Member>> findAllMembers(){
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    // Update an existing member
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Member updatedMember){
        return ResponseEntity.ok(memberService.updateMember(id, updatedMember));
    }

    // Get a member by its ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Member> findMemberById(@PathVariable Long id){
        return memberService.findMemberById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get a member by its email
    @GetMapping("/email/{email}")
    public ResponseEntity<Member> findMemberByEmail(@PathVariable String email){
        return memberService.findMemberByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a member by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
