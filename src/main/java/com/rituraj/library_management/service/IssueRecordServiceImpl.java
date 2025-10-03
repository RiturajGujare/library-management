package com.rituraj.library_management.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rituraj.library_management.model.Book;
import com.rituraj.library_management.model.IssueRecord;
import com.rituraj.library_management.model.IssueStatus;
import com.rituraj.library_management.model.Member;
import com.rituraj.library_management.repository.BookRepository;
import com.rituraj.library_management.repository.IssueRecordRepository;
import com.rituraj.library_management.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class IssueRecordServiceImpl implements IssueRecordService{
    
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final IssueRecordRepository issueRecordRepository;

    @Override
    public IssueRecord issueBook(Book book, Member member, int loanPeriodDays){
        if(!member.getActive()){
            throw new IllegalArgumentException("Member is not active member");
        }

        else if(book.getAvailableCopies() <= 0){
            throw new IllegalArgumentException("No books available with title " + book.getTitle());
        }

        boolean alreadyIssued = issueRecordRepository.existsByBookAndMemberAndStatus(book, member, IssueStatus.ISSUED);

        if(alreadyIssued){
            throw new IllegalArgumentException("Book is already issued to the member");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        IssueRecord issueRecord = IssueRecord.builder()
                                    .book(book)
                                    .member(member)
                                    .issueDate(LocalDate.now())
                                    .dueDate(LocalDate.now().plusDays(loanPeriodDays))
                                    .status(IssueStatus.ISSUED)
                                    .build();

        return issueRecordRepository.save(issueRecord);
    }

    @Override
    public IssueRecord returnBook(Long issueRecordId){
        IssueRecord issueRecord = issueRecordRepository.findById(issueRecordId)
                                                    .orElseThrow(() -> new IllegalArgumentException("No record found"));

        if(issueRecord.getStatus() == IssueStatus.RETURNED){
            throw new IllegalArgumentException("Book already returned");
        }

        Book book = issueRecord.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        issueRecord.setStatus(IssueStatus.RETURNED);
        issueRecord.setReturnDate(LocalDate.now());

        return issueRecordRepository.save(issueRecord);

    }

    @Override
    public List<IssueRecord> findAll(){
        return issueRecordRepository.findAll();
    }

    @Override
    public Optional<IssueRecord> findIssueRecordById(Long id){
        return issueRecordRepository.findById(id);
    }

    @Override
    public List<IssueRecord> findIssuedBooksByMember(Member member){
        return issueRecordRepository.findByMemberAndStatus(member, IssueStatus.ISSUED);
    }

    @Override
    public List<IssueRecord> findOverDueBooksByMember(Member member){
        return issueRecordRepository.findByMemberAndStatus(member, IssueStatus.OVERDUE);
    }

    @Override
    public void markOverDue(){
        List<IssueRecord> overDueRecords = issueRecordRepository.findByDueDateBeforeAndStatus(LocalDate.now(), IssueStatus.ISSUED);

        for(IssueRecord record: overDueRecords){
            record.setStatus(IssueStatus.OVERDUE);
        }
        issueRecordRepository.saveAll(overDueRecords);
    }
}
