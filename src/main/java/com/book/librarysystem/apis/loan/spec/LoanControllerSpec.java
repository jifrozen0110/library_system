package com.book.librarysystem.apis.loan.spec;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.book.librarysystem.applications.loan.request.LoanRegisterRequest;
import com.book.librarysystem.globals.presentation.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface LoanControllerSpec {

	@Operation(
		summary = "도서 대출",
		description = "사용자가 특정 도서를 대출합니다. 반납 예정일은 대출일 기준 2주 후로 자동 설정됩니다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "대출 등록 성공",
				content = @Content(schema = @Schema(implementation = Long.class),
					examples = @ExampleObject(value = "10"))),
			@ApiResponse(responseCode = "400", description = "잘못된 요청 데이터",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class),
					examples = {
						@ExampleObject(name = "MissingLoanDate",
							summary = "대출일이 없는 경우",
							value = "{ \"message\": \"대출일은 필수입니다.\" }"),
						@ExampleObject(name = "InvalidLoanDateFormat",
							summary = "대출일 형식이 잘못된 경우",
							value = "{ \"message\": \"대출일은 yyyy-MM-dd 형식이어야 합니다.\" }"),
						@ExampleObject(name = "MissingBookId",
							summary = "도서 ID가 없는 경우",
							value = "{ \"message\": \"도서 ID는 필수입니다.\" }"),
						@ExampleObject(name = "MissingUserId",
							summary = "사용자 ID가 없는 경우",
							value = "{ \"message\": \"사용자 ID는 필수입니다.\" }")
					}
				))
		}
	)
	@PostMapping
	ResponseEntity<Long> registerLoan(
		@RequestBody @Valid LoanRegisterRequest request
	);

	@Operation(
		summary = "대출 상태 확인",
		description = "특정 도서가 현재 대출 중인지 확인합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "대출 상태 확인 성공",
				content = @Content(schema = @Schema(implementation = Boolean.class),
					examples = {
						@ExampleObject(name = "BookLoaned",
							summary = "도서가 대출 중인 경우",
							value = "true"),
						@ExampleObject(name = "BookAvailable",
							summary = "도서가 대출되지 않은 경우",
							value = "false")
					})),
			@ApiResponse(responseCode = "404", description = "해당 도서를 찾을 수 없습니다.",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class),
					examples = @ExampleObject(value = "{ \"message\": \"해당 도서를 찾을 수 없습니다.\" }")))
		}
	)
	@GetMapping("/status/{bookId}")
	ResponseEntity<Boolean> checkLoanStatus(
		@Parameter(description = "대출 상태를 확인할 도서 ID", example = "1")
		@PathVariable Long bookId
	);

	@Operation(
		summary = "도서 반납",
		description = "대출된 도서를 반납합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "도서 반납 성공",
				content = @Content(schema = @Schema(implementation = String.class),
					examples = @ExampleObject(value = "{ \"message\": \"도서 반납이 완료되었습니다.\" }"))),
			@ApiResponse(responseCode = "404", description = "해당 대출 기록을 찾을 수 없습니다.",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class),
					examples = @ExampleObject(value = "{ \"message\": \"대출을 찾을 수 없습니다.\" }")))
		}
	)
	@PatchMapping("/{loanId}/return")
	ResponseEntity<String> returnLoan(
		@Parameter(description = "반납 처리할 대출 ID", example = "1")
		@PathVariable Long loanId
	);
}
