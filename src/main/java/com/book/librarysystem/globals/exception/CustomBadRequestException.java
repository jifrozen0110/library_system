package com.book.librarysystem.globals.exception;

public class CustomBadRequestException extends RuntimeException {

	public CustomBadRequestException(String message) {
		super(message);
	}

	public CustomBadRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
