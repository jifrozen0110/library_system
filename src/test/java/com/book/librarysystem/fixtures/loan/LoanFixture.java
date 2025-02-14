package com.book.librarysystem.fixtures.loan;

import com.book.librarysystem.applications.loan.request.LoanRegisterRequest;

public final class LoanFixture {
	public static final LoanRegisterRequest loanRegisterRequest = new LoanRegisterRequest("2023-05-12",
		1L, 1L
	);

	public static LoanRegisterRequest getLoanRegisterRequest(Long bookId, Long userId) {
		return new LoanRegisterRequest("2023-05-12", bookId, userId);
	}
}
