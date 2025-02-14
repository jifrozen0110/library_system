package com.book.librarysystem.applications.loan;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.book.librarysystem.applications.ServiceTestSupport;
import com.book.librarysystem.applications.book.BookService;
import com.book.librarysystem.applications.loan.request.LoanRegisterRequest;
import com.book.librarysystem.applications.user.UserService;
import com.book.librarysystem.domains.loan.domain.Loan;
import com.book.librarysystem.domains.loan.domain.LoanStatus;
import com.book.librarysystem.domains.loan.exception.BookAlreadyLoanedException;
import com.book.librarysystem.domains.loan.exception.LoanNotFoundException;
import com.book.librarysystem.domains.loan.exception.LoanedNotStatusException;
import com.book.librarysystem.domains.loan.repository.LoanRepository;
import com.book.librarysystem.fixtures.book.BookFixture;
import com.book.librarysystem.fixtures.loan.LoanFixture;
import com.book.librarysystem.fixtures.user.UserFixture;
import com.book.librarysystem.globals.util.DateTimeConverter;

@DisplayName("[서비스] Loan Service")
class LoanServiceTest extends ServiceTestSupport {

	@Autowired
	private LoanService loanService;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	private LoanRegisterRequest preRegisterBookAndUser() {
		Long bookId = bookService.registerBook(BookFixture.bookRegisterRequest);
		Long userId = userService.join(UserFixture.userJoinRequest);
		return LoanFixture.getLoanRegisterRequest(bookId, userId);
	}

	@Nested
	@DisplayName("대출 등록")
	class CreateLoan {
		@Test
		@DisplayName("대출 중인 도서가 없으면 대출을 생성하고 ID를 반환한다.")
		void success() {

			LoanRegisterRequest request = preRegisterBookAndUser();
			Long loanId = loanService.createdLoan(request);

			Loan savedLoan = loanService.getLoan(loanId);
			assertThat(savedLoan.getBookId()).isEqualTo(request.bookId());
			assertThat(savedLoan.getUserId()).isEqualTo(request.userId());
			assertThat(savedLoan.getDueDate()).isEqualTo(DateTimeConverter.parseDate(request.loanDate()).plusWeeks(2));
			assertThat(savedLoan.getStatus()).isEqualTo(LoanStatus.LOANED);
		}

		@Test
		@DisplayName("이미 대출 중인 도서이면 BookAlreadyLoanedException을 발생시킨다.")
		void alreadyLoanedThenThrowException() {

			LoanRegisterRequest request = preRegisterBookAndUser();

			loanService.createdLoan(request);

			assertThatThrownBy(() -> loanService.createdLoan(request))
				.isInstanceOf(BookAlreadyLoanedException.class);
		}
	}

	@Test
	@DisplayName("대출 중이면 true를, 아니면 false를 반환")
	void isBookLoaned() {
		LoanRegisterRequest request = preRegisterBookAndUser();

		assertThat(loanService.isBookLoaned(request.bookId())).isFalse();

		loanService.createdLoan(request);
		assertThat(loanService.isBookLoaned(request.bookId())).isTrue();
	}

	@Nested
	@DisplayName("대출 반납")
	class ReturnLoan {
		@Test
		@DisplayName("대출 상태인 도서를 반납하면 상태가 RETURNED로 변경")
		void success() {
			LoanRegisterRequest request = preRegisterBookAndUser();
			Long loanId = loanService.createdLoan(request);

			loanService.returnLoan(loanId);

			Loan loan = loanService.getLoan(loanId);
			assertThat(loan.getStatus()).isEqualTo(LoanStatus.RETURNED);
		}

		@Test
		@DisplayName("대출 상태가 LOANED가 아니면 LoanedNotStatusException을 발생")
		void notLoanedThenThrowException() {
			LoanRegisterRequest request = preRegisterBookAndUser();
			Long loanId = loanService.createdLoan(request);

			Loan loan = loanService.getLoan(loanId);
			loan.returnLoan();

			assertThatThrownBy(() -> loanService.returnLoan(loanId))
				.isInstanceOf(LoanedNotStatusException.class);
		}
	}

	@Test
	@DisplayName("존재하는 대출 정보를 반환")
	void getLoan() {

		LoanRegisterRequest request = preRegisterBookAndUser();
		Long loanId = loanService.createdLoan(request);

		Loan retrievedLoan = loanService.getLoan(loanId);

		assertThat(retrievedLoan).isNotNull();
		assertThat(retrievedLoan.getId()).isEqualTo(loanId);
	}

	@Test
	@DisplayName("존재하지 않는 대출이면 LoanNotFoundException을 발생")
	void notFoundLoanThenThrowException() {
		Long loanId = 999L;

		assertThatThrownBy(() -> loanService.getLoan(loanId))
			.isInstanceOf(LoanNotFoundException.class);
	}

}
