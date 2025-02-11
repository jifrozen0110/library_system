package com.book.librarysystem.applications.user.response;

import com.book.librarysystem.domains.user.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 응답 DTO")
public record UserResponse(
	@Schema(description = "사용자 ID", example = "1")
	Long id,
	@Schema(description = "사용자 이메일", example = "answldjs1836@gmail.com")
	String email,
	@Schema(description = "사용자 이름", example = "문지언")
	String name
) {
	public static UserResponse of(User user) {
		return new UserResponse(user.getId(), user.getEmail().getValue(), user.getName().getValue());
	}
}
