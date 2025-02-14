package com.book.librarysystem.domains.loan.exception;

import com.book.librarysystem.globals.exception.CustomNotFoundException;

public class LoanNotFoundException extends CustomNotFoundException {
	public LoanNotFoundException() {
		super("대출을 찾을 수 없습니다.");
	}
}
