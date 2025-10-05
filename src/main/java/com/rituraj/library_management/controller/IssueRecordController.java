package com.rituraj.library_management.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rituraj.library_management.model.Book;
import com.rituraj.library_management.model.IssueRecord;
import com.rituraj.library_management.model.Member;
import com.rituraj.library_management.service.BookService;
import com.rituraj.library_management.service.IssueRecordService;
import com.rituraj.library_management.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/issuerecords")
public class IssueRecordController {

    private final IssueRecordService issueRecordService;
    private final BookService bookService;
    private final MemberService memberService;

    @PostMapping("/issue")
    public ResponseEntity<IssueRecord> issueBook(@RequestParam Long bookId,  @RequestParam Long memberId,
                                                    @RequestParam int loanPeriodDays){
        
        Book book = bookService.findBookById(bookId)
                    .orElseThrow(() -> new IllegalArgumentException("No Book found"));

        Member member = memberService.findMemberById(memberId)
                        .orElseThrow(() -> new IllegalArgumentException("No Member found"));

        IssueRecord issueRecord = issueRecordService.issueBook(book, member, loanPeriodDays);
        
        return ResponseEntity.ok(issueRecord);
    }

    @PutMapping("/return/{issueRecordId}")
    public ResponseEntity<IssueRecord> returnBook(@PathVariable Long issueRecordId){
        IssueRecord issueRecord = issueRecordService.returnBook(issueRecordId);
        return ResponseEntity.ok(issueRecord);
    }


    @GetMapping()
    public ResponseEntity<List<IssueRecord>> findAllRecords(){
        return ResponseEntity.ok(issueRecordService.findAll());
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<IssueRecord> findIssueRecordById(@PathVariable Long id){
        return issueRecordService.findIssueRecordById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<IssueRecord>> findIssuedBooksByMember(@PathVariable Long memberId){
        Member member = memberService.findMemberById(memberId)
                        .orElseThrow(() -> new IllegalArgumentException("No member found"));

        List<IssueRecord> issueRecords = issueRecordService.findIssuedBooksByMember(member);

        return ResponseEntity.ok(issueRecords);
    }

    @GetMapping("/member/{memberId}/overdue")
    public ResponseEntity<List<IssueRecord>> findOverDueBooksByMember(@PathVariable Long memberId){
        Member member = memberService.findMemberById(memberId)
                        .orElseThrow(() -> new IllegalArgumentException("No member found"));

        List<IssueRecord> overDueRecords = issueRecordService.findOverDueBooksByMember(member);
        return ResponseEntity.ok(overDueRecords);
    }

    @PutMapping("/mark-overdue")
    public ResponseEntity<Void> markOverDue(){
        issueRecordService.markOverDue();
        return ResponseEntity.noContent().build();
    }

}
