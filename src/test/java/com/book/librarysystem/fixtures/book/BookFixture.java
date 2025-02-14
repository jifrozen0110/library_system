package com.book.librarysystem.fixtures.book;

import java.util.List;

import com.book.librarysystem.applications.book.request.BookDeleteRequest;
import com.book.librarysystem.applications.book.request.BookRegisterRequest;
import com.book.librarysystem.applications.book.request.BookUpdateRequest;
import com.book.librarysystem.applications.book.response.BookResponse;

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

	public static final BookResponse bookResponse = new BookResponse(
		1L, "자바의 정석", "남궁성", "2021-01-01"
	);

	public static final BookResponse bookResponse2 = new BookResponse(
		2L, "스프링 부트 인 액션", "김영한", "2022-03-01"
	);
	public static final List<BookResponse> bookResponses = List.of(
		bookResponse, bookResponse2
	);

	private BookFixture() {
	}
}
