package com.book.librarysystem.apis.user.spec;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.book.librarysystem.applications.user.request.UserJoinRequest;
import com.book.librarysystem.applications.user.response.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

public interface UserControllerSpec {
	@Operation(
		summary = "사용자 회원가입",
		description = "새로운 사용자를 등록합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "회원가입 성공",
				content = @Content(schema = @Schema(implementation = Long.class))),
			@ApiResponse(responseCode = "400", description = "잘못된 요청 데이터입니다.")
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
			@ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없습니다.")
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
