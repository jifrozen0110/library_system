package com.book.librarysystem.applications.book.response;

import java.io.Serializable;

import com.book.librarysystem.domains.book.domain.Book;
import com.book.librarysystem.globals.util.DateTimeConverter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "도서 조회 응답 Response")
public record BookResponse(
	@Schema(description = "도서 ID", example = "1")
	Long id,
	@Schema(description = "도서 제목", example = "자바의 정석")
	String title,
	@Schema(description = "도서 저자", example = "남궁성")
	String author,
	@Schema(description = "도서 출판일", example = "2021-01-01")
	String publicationDate
) implements Serializable {
	public static BookResponse from(Book book) {
		String publicationDate = DateTimeConverter.formatDate(book.getPublishedAt());
		return new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), publicationDate);
	}
}
