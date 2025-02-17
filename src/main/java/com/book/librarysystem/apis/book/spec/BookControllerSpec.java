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
import com.book.librarysystem.applications.book.response.BookDetailResponse;
import com.book.librarysystem.applications.book.response.BookResponse;
import com.book.librarysystem.globals.presentation.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
			@ApiResponse(responseCode = "404", description = "not found",
				content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = ErrorResponse.class),
					examples = @ExampleObject(value = "책을 찾을 수 없습니다.")))
		}
	)
	@GetMapping("/{id}")
	ResponseEntity<BookDetailResponse> getBook(
		@Parameter(description = "조회할 도서 ID", example = "1") @PathVariable Long id
	);

	@Operation(
		summary = "도서 등록",
		description = "새로운 도서를 등록합니다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "도서 등록 성공",
				content = @Content(schema = @Schema(implementation = Long.class))),
			@ApiResponse(responseCode = "400", description = "bad request",
				content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = ErrorResponse.class),
					examples = {
						@ExampleObject(name = "MissingTitle",
							summary = "도서 제목이 없는 경우",
							value = "{ \"message\": \"도서 제목은 필수입니다.\" }"),
						@ExampleObject(name = "MissingAuthor",
							summary = "도서 저자가 없는 경우",
							value = "{ \"message\": \"도서 저자는 필수입니다.\" }"),
						@ExampleObject(name = "MissingPublicationDate",
							summary = "도서 출판일이 없는 경우",
							value = "{ \"message\": \"도서 출판일은 필수입니다.\" }"),
						@ExampleObject(name = "InvalidDateFormat",
							summary = "출판일 형식이 잘못된 경우",
							value = "{ \"message\": \"도서 출판일은 yyyy-MM-dd 형식이어야 합니다.\" }")
					}
				))
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
			@ApiResponse(responseCode = "404", description = "not found",
				content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = ErrorResponse.class),
					examples = @ExampleObject(value = "책을 찾을 수 없습니다."))),
			@ApiResponse(responseCode = "400", description = "bad request",
				content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = ErrorResponse.class),
					examples = {
						@ExampleObject(name = "MissingTitle",
							summary = "도서 제목이 없는 경우",
							value = "{ \"message\": \"도서 제목은 필수입니다.\" }"),
						@ExampleObject(name = "MissingAuthor",
							summary = "도서 저자가 없는 경우",
							value = "{ \"message\": \"도서 저자는 필수입니다.\" }"),
						@ExampleObject(name = "MissingPublicationDate",
							summary = "도서 출판일이 없는 경우",
							value = "{ \"message\": \"도서 출판일은 필수입니다.\" }"),
						@ExampleObject(name = "InvalidDateFormat",
							summary = "출판일 형식이 잘못된 경우",
							value = "{ \"message\": \"도서 출판일은 yyyy-MM-dd 형식이어야 합니다.\" }")
					}
				))
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
			@ApiResponse(responseCode = "404", description = "not found",
				content = @Content(mediaType = "application/json",
					schema = @Schema(implementation = ErrorResponse.class),
					examples = @ExampleObject(value = "책을 찾을 수 없습니다."))),
			@ApiResponse(responseCode = "400", description = "bad request",
				content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = ErrorResponse.class),
					examples = {
						@ExampleObject(name = "MissingDeleteBy",
							summary = "삭제자가 없는 경우",
							value = "{ \"message\": \"삭제자는 필수입니다.\" }"),
					}
				))
		}
	)
	@DeleteMapping("/{id}")
	ResponseEntity<String> deleteBook(
		@Parameter(description = "삭제할 도서 ID", example = "1") @PathVariable Long id,
		@RequestBody @Valid BookDeleteRequest request
	);
}
