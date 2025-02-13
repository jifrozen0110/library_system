package com.book.librarysystem.domains.user.exception;

public class UserNameLengthException extends RuntimeException {
	public UserNameLengthException(int allowedLength, int actualLength) {
		super(String.format("멤버 이름의 길이가 최대 이름 길이를 초과했습니다. - 요청 정보 { 최대 길이 : %d, 입력한 길이 : %d }",
			allowedLength,
			actualLength));
	}
}
