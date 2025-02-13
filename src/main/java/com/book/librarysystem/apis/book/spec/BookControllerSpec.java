package com.book.librarysystem.apis.book.spec;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.book.librarysystem.applications.book.request.BookDeleteRequest;
import com.book.librarysystem.applications.book.request.BookRegisterRequest;
import com.book.librarysystem.applications.book.request.BookUpdateRequest;
import com.book.librarysystem.applications.book.response.BookResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface BookControllerSpec {

	@Operation(
		summary = "도서 목록 조회",
		description = "등록된 모든 도서를 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "전체 도서 조회 성공",
				content = @Content(array = @ArraySchema(schema = @Schema(implementation = BookResponse.class)))),
			@ApiResponse(responseCode = "500", description = "서버 내부 오류")
		}
	)
	@GetMapping
	ResponseEntity<List<BookResponse>> getBooks();

	@Operation(
		summary = "도서 조회",
		description = "ID를 기준으로 도서를 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "도서 조회 성공",
				content = @Content(schema = @Schema(implementation = BookResponse.class))),
			@ApiResponse(responseCode = "404", description = "도서를 찾을 수 없습니다.")
		}
	)
	@GetMapping("/{id}")
	ResponseEntity<BookResponse> getBook(
		@Parameter(description = "조회할 도서 ID", example = "1") @PathVariable Long id
	);

	@Operation(
		summary = "도서 등록",
		description = "새로운 도서를 등록합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "도서 등록 성공",
				content = @Content(schema = @Schema(implementation = Long.class))),
			@ApiResponse(responseCode = "400", description = "잘못된 요청 데이터입니다.")
		}
	)
	@PostMapping
	ResponseEntity<Long> createBook(
		@RequestBody @Valid BookRegisterRequest request
	);

	@Operation(
		summary = "도서 수정",
		description = "기존 도서의 정보를 업데이트합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "도서 수정 성공",
				content = @Content(schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "400", description = "잘못된 요청 데이터입니다."),
			@ApiResponse(responseCode = "404", description = "도서를 찾을 수 없습니다.")
		}
	)
	@PutMapping("/{id}")
	ResponseEntity<String> updateBook(
		@Parameter(description = "수정할 도서 ID", example = "1") @PathVariable Long id,
		@RequestBody @Valid BookUpdateRequest request
	);

	@Operation(
		summary = "도서 삭제",
		description = "도서를 삭제합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "도서 삭제 성공",
				content = @Content(schema = @Schema(implementation = String.class))),
			@ApiResponse(responseCode = "400", description = "잘못된 요청 데이터입니다."),
			@ApiResponse(responseCode = "404", description = "도서를 찾을 수 없습니다.")
		}
	)
	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteBook(
		@Parameter(description = "삭제할 도서 ID", example = "1") @PathVariable Long id,
		@RequestBody @Valid BookDeleteRequest request
	);
}
