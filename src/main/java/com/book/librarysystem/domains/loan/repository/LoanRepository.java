package com.book.librarysystem.domains.loan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.librarysystem.domains.loan.domain.Loan;
import com.book.librarysystem.domains.loan.domain.LoanStatus;

public interface LoanRepository extends JpaRepository<Loan, Long> {
	Optional<Loan> findByBookIdAndStatus(Long bookId, LoanStatus status);
}
