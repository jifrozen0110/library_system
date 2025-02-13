package com.book.librarysystem.domains.user.exception;

public class UserEmailInvalidException extends RuntimeException {
	public UserEmailInvalidException(String value) {
		super("이메일 형식이 올바르지 않습니다: " + value);
	}
}
