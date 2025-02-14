package com.book.librarysystem.applications.loan;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.librarysystem.applications.book.BookService;
import com.book.librarysystem.applications.loan.request.LoanRegisterRequest;
import com.book.librarysystem.applications.user.UserService;
import com.book.librarysystem.domains.book.domain.Book;
import com.book.librarysystem.domains.book.exception.BookDeletedException;
import com.book.librarysystem.domains.loan.domain.Loan;
import com.book.librarysystem.domains.loan.domain.LoanStatus;
import com.book.librarysystem.domains.loan.exception.BookAlreadyLoanedException;
import com.book.librarysystem.domains.loan.exception.LoanNotFoundException;
import com.book.librarysystem.domains.loan.exception.LoanedNotStatusException;
import com.book.librarysystem.domains.loan.repository.LoanRepository;
import com.book.librarysystem.domains.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoanService {

	private final LoanRepository loanRepository;

	private final BookService bookService;

	private final UserService userService;

	@Transactional
	public Long createdLoan(LoanRegisterRequest request) {
		Book book = bookService.getBook(request.bookId());
		User user= userService.getUser(request.userId());
		if (book.getDeletedAt() != null) {
			throw new BookDeletedException(book.getId());
		}
		Optional<Loan> existingLoan = loanRepository.findByBookIdAndStatus(request.bookId(), LoanStatus.LOANED);
		if (existingLoan.isPresent()) {
			throw new BookAlreadyLoanedException();
		}

		Loan loan = request.toLoan();
		loan = loanRepository.save(loan);
		return loan.getId();
	}

	@Transactional(readOnly = true)
	public boolean isBookLoaned(Long bookId) {
		return loanRepository.findByBookIdAndStatus(bookId, LoanStatus.LOANED).isPresent();
	}

	@Transactional
	public void returnLoan(Long loanId) {
		Loan loan = getLoan(loanId);
		if (loan.getStatus() != LoanStatus.LOANED) {
			throw new LoanedNotStatusException(loan.getId(), loan.getStatus());
		}
		loan.returnLoan();
		loanRepository.save(loan);
	}

	public Loan getLoan(Long loanId) {
		return loanRepository.findById(loanId)
			.orElseThrow(() -> new LoanNotFoundException());
	}
}
