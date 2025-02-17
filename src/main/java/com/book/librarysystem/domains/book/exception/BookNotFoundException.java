package com.book.librarysystem.domains.book.exception;

import com.book.librarysystem.globals.exception.CustomNotFoundException;

public class BookNotFoundException extends CustomNotFoundException {
	public BookNotFoundException() {
		super("책을 찾을 수 없습니다.");
	}
}
