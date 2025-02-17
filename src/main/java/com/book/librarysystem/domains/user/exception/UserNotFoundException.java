package com.book.librarysystem.domains.user.exception;

import com.book.librarysystem.globals.exception.CustomNotFoundException;

public class UserNotFoundException extends CustomNotFoundException {
	public UserNotFoundException() {
		super("사용자를 찾을 수 없습니다.");
	}
}
