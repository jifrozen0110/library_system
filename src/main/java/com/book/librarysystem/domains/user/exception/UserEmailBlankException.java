package com.book.librarysystem.domains.user.exception;

import com.book.librarysystem.globals.exception.CustomBadRequestException;

public class UserEmailBlankException extends CustomBadRequestException {
	public UserEmailBlankException() {
		super("이메일은 null 이거나 공백일 수 없습니다.");
	}
}
