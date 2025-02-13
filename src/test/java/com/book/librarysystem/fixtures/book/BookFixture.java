package com.book.librarysystem.fixtures.book;

import com.book.librarysystem.applications.book.request.BookDeleteRequest;
import com.book.librarysystem.applications.book.request.BookRegisterRequest;
import com.book.librarysystem.applications.book.request.BookUpdateRequest;

public final class BookFixture {

	public static final BookRegisterRequest bookRegisterRequest = new BookRegisterRequest(
		"자바의 정석", "남궁성", "2021-01-01"
	);

	public static final BookRegisterRequest bookRegisterRequest2 = new BookRegisterRequest(
		"스프링 부트 인 액션", "김영한", "2022-03-01"
	);

	public static final BookUpdateRequest bookUpdateRequest = new BookUpdateRequest(
		"자바의 정석 - 개정판", "남궁성", "2021-05-01", "admin"
	);

	public static final BookDeleteRequest bookDeleteRequest = new BookDeleteRequest("admin");

	private BookFixture() {
		// 인스턴스화 방지
	}
}
