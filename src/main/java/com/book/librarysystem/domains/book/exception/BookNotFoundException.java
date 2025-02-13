package com.book.librarysystem.domains.book.exception;

public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException() {
		super("책을 찾을 수 없습니다.");
	}
}
