package com.book.librarysystem.applications.book.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "도서 수정 요청 Request")
public record BookUpdateRequest(
	@Schema(description = "도서 제목", example = "자바의 정석")
	@NotBlank(message = "도서 제목은 필수입니다.")
	String title,

	@Schema(description = "도서 저자", example = "남궁성")
	@NotBlank(message = "도서 저자는 필수입니다.")
	String author,

	@Schema(description = "도서 출판일", example = "2021-01-01")
	@NotBlank(message = "도서 출판일은 필수입니다.")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "도서 출판일은 yyyy-MM-dd 형식이어야 합니다.")
	String publicationDate,

	@Schema(description = "수정자", example = "admin")
	@NotBlank(message = "수정자는 필수입니다.")
	String modifiedBy
) {
}
