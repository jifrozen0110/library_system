package com.book.librarysystem.domains.book.exception;

import com.book.librarysystem.globals.exception.CustomBadRequestException;

public class BookDeletedException extends CustomBadRequestException {
	public BookDeletedException(Long bookId) {
		super("삭제된 책입니다. 책 ID: " + bookId);
	}
}
