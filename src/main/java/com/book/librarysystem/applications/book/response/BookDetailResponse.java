package com.book.librarysystem.applications.book.response;

import java.io.Serializable;

import com.book.librarysystem.domains.book.domain.Book;
import com.book.librarysystem.globals.util.DateTimeConverter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "도서 조회 응답 Response")
public record BookDetailResponse(
	@Schema(description = "도서 ID", example = "1")
	Long id,
	@Schema(description = "도서 제목", example = "자바의 정석")
	String title,
	@Schema(description = "도서 저자", example = "남궁성")
	String author,
	@Schema(description = "도서 출판일", example = "2021-01-01")
	String publicationDate,
	@Schema(description = "도서 삭제자", example = "admin")
	String deletedBy,
	@Schema(description = "도서 삭제일", example = "2021-01-01")
	String deletedAt,
	@Schema(description = "도서 수정자", example = "admin")
	String modifiedBy

) implements Serializable {
	public static BookDetailResponse from(Book book) {
		String publicationDate = DateTimeConverter.formatDate(book.getPublishedAt());
		String deletedAt = DateTimeConverter.formatDateTime(book.getDeletedAt());
		return new BookDetailResponse(book.getId(), book.getTitle(), book.getAuthor(), publicationDate, deletedAt,
			book.getDeletedBy(), book.getModifiedBy());
	}
}
