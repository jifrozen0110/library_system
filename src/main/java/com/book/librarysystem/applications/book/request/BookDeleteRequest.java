package com.book.librarysystem.applications.book.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "도서 삭제 요청 Request")
public record BookDeleteRequest(
	@Schema(description = "삭제자", example = "admin")
	@NotBlank(message = "삭제자는 필수입니다.")
	String deleteBy
) {
}
