package com.book.librarysystem.domains.loan.domain;

import java.time.LocalDate;

import com.book.librarysystem.domains.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Loan extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate loanDate;
	@Column(nullable = false)
	private LocalDate dueDate;
	private LocalDate returnDate;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private LoanStatus status;

	@Column(name = "book_id", nullable = false)
	private Long bookId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	private Loan(LocalDate loanDate, LocalDate dueDate, Long bookId, Long userId) {
		this.loanDate = loanDate;
		this.dueDate = dueDate;
		this.bookId = bookId;
		this.userId = userId;
		this.status = LoanStatus.LOANED;
	}

	public static Loan createLoan(LocalDate loanDate, Long bookId, Long userId) {
		return new Loan(loanDate, loanDate.plusWeeks(2), bookId, userId);
	}

	public void returnLoan() {
		this.returnDate = LocalDate.now();
		this.status = LoanStatus.RETURNED;
	}

}
