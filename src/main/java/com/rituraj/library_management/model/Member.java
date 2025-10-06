package com.rituraj.library_management.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity 
@Table(name = "members")  // This annotation specifies the name of the database table to be used for mapping
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

/*
 * Member Entity
 * Represents a member in the library management system
 */
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //unique = true ensures that the email is unique across all member records
    @Column(nullable = false, unique = true)
    private String email;

    private LocalDate membershipDate;  // Can be null if the date is not set

    // active indicates whether the member's account is active or deactivated
    @Column(nullable = false)
    private Boolean active;

}
