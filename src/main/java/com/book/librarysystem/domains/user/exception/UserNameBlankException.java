package com.book.librarysystem.domains.user.exception;

public class UserNameBlankException extends RuntimeException{
	public UserNameBlankException() {
		super("이름은 null 이거나 공백일 수 없습니다.");
	}
}
