package com.book.librarysystem.domains.user.exception;

import com.book.librarysystem.globals.exception.CustomBadRequestException;

public class UserEmailInvalidException extends CustomBadRequestException {
	public UserEmailInvalidException(String value) {
		super("이메일 형식이 올바르지 않습니다: " + value);
	}
}
