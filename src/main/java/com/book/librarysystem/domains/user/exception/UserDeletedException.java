package com.book.librarysystem.domains.user.exception;

import com.book.librarysystem.globals.exception.CustomBadRequestException;

public class UserDeletedException extends CustomBadRequestException {
	public UserDeletedException(Long userId) {
		super("삭제된 사용자입니다. 사용자 ID: " + userId);
	}
}
