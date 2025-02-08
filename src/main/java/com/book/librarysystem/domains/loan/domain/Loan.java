package com.book.librarysystem.domains.loan.domain;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_loan")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate loanDate;
	@Column(nullable = false)
	private LocalDate dueDate;
	private LocalDate returnDate;

	@Column(length = 10)
	private String status;

	@Column(name = "book_id", nullable = false)
	private Long bookId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

}
