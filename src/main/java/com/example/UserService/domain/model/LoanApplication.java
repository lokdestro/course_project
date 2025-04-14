package com.example.UserService.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "loan_applications")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    private Integer amount;

    @Column(name = "term_months")
    private Integer termMonths;

    @Column(name = "interest_rate")
    private Integer interestRate;

    private String status;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "decision_date")
    private LocalDateTime decisionDate;
}