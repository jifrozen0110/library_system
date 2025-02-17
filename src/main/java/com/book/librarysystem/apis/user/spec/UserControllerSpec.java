package com.book.librarysystem.apis.user.spec;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.book.librarysystem.applications.user.request.UserJoinRequest;
import com.book.librarysystem.applications.user.response.UserResponse;
import com.book.librarysystem.globals.presentation.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface UserControllerSpec {
	@Operation(
		summary = "사용자 회원가입",
		description = "새로운 사용자를 등록합니다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "회원가입 성공",
				content = @Content(schema = @Schema(implementation = Long.class),
					examples = @ExampleObject(value = "1"))),
			@ApiResponse(responseCode = "400", description = "잘못된 요청 데이터",
				content = @Content(
					schema = @Schema(implementation = ErrorResponse.class),
					examples = {
						@ExampleObject(name = "MissingEmail",
							summary = "이메일이 없는 경우",
							value = "{ \"message\": \"이메일은 필수 입력값입니다.\" }"),
						@ExampleObject(name = "InvalidEmailFormat",
							summary = "이메일 형식이 잘못된 경우",
							value = "{ \"message\": \"올바른 이메일 형식이 아닙니다.\" }"),
						@ExampleObject(name = "MissingName",
							summary = "이름이 없는 경우",
							value = "{ \"message\": \"이름은 필수 입력값입니다.\" }"),
						@ExampleObject(name = "InvalidNameLength",
							summary = "이름 길이가 유효하지 않은 경우",
							value = "{ \"message\": \"이름은 2자 이상 100자 이하로 입력해야 합니다.\" }")
					}
				))
		}
	)
	@PostMapping("/join")
	ResponseEntity<Long> join(
		@RequestBody @Valid UserJoinRequest request
	);

	@Operation(
		summary = "사용자 조회",
		description = "ID를 기준으로 사용자를 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "사용자 조회 성공",
				content = @Content(schema = @Schema(implementation = UserResponse.class))),
			@ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없습니다.",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class),
					examples = @ExampleObject(value = "{ \"message\": \"사용자를 찾을 수 없습니다.\" }")))
		}
	)
	@GetMapping("/{id}")
	ResponseEntity<UserResponse> getUser(
		@Parameter(description = "조회할 사용자 ID", example = "1") @PathVariable Long id
	);

	@Operation(
		summary = "전체 사용자 조회",
		description = "등록된 모든 사용자를 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "전체 사용자 조회 성공",
				content = @Content(schema = @Schema(implementation = UserResponse.class))),
		}
	)
	@GetMapping
	ResponseEntity<List<UserResponse>> getAllUsers();
}
