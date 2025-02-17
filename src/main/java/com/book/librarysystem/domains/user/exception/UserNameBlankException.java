package com.book.librarysystem.domains.user.exception;

import com.book.librarysystem.globals.exception.CustomBadRequestException;

public class UserNameBlankException extends CustomBadRequestException {
	public UserNameBlankException() {
		super("이름은 null 이거나 공백일 수 없습니다.");
	}
}
