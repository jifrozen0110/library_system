package com.book.librarysystem.globals.exception;

public class CustomDateFormatException extends CustomBadRequestException {
	public CustomDateFormatException(Exception e) {
		super("날짜 형식이 잘못되었습니다.", e);
	}
}
