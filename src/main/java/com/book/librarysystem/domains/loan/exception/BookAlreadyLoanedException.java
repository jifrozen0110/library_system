package com.book.librarysystem.domains.loan.exception;

import com.book.librarysystem.globals.exception.CustomBadRequestException;

public class BookAlreadyLoanedException extends CustomBadRequestException {

	public BookAlreadyLoanedException() {
		super("이미 대출된 책입니다.");
	}
}
