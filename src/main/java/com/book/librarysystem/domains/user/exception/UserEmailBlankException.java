package com.book.librarysystem.domains.user.exception;

public class UserEmailBlankException extends RuntimeException {
	public UserEmailBlankException() {
		super("이메일은 null 이거나 공백일 수 없습니다.");
	}
}
