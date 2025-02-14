package com.book.librarysystem.domains.loan.exception;

import com.book.librarysystem.domains.loan.domain.LoanStatus;
import com.book.librarysystem.globals.exception.CustomBadRequestException;

public class LoanedNotStatusException extends CustomBadRequestException {
	public LoanedNotStatusException(Long loanId, LoanStatus status) {
		super("대출 상태가 올바르지 않습니다. 현재 상태: " + status + " 대출 ID: " + loanId);
	}
}
