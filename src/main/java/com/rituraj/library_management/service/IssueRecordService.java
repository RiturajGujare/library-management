package com.rituraj.library_management.service;

import java.util.List;
import java.util.Optional;

import com.rituraj.library_management.model.Book;
import com.rituraj.library_management.model.IssueRecord;
import com.rituraj.library_management.model.Member;

public interface IssueRecordService {
    

    IssueRecord issueBook(Book book, Member member, int loanPeriodDays);

    IssueRecord returnBook(Long issueRecordId);

    List<IssueRecord> findAll();

    Optional<IssueRecord> findIssueRecordById(Long id);

    List<IssueRecord> findIssuedBooksByMember(Member member);

    List<IssueRecord> findOverDueBooksByMember(Member member);

    void markOverDue();

}
