package com.book.librarysystem.applications.user.request;

import com.book.librarysystem.domains.user.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "사용자 등록 요청 Request")
public record UserJoinRequest(
	@Schema(description = "사용자 이메일", example = "answldjs1836@gmail.com")
	@NotBlank(message = "이메일은 필수 입력값입니다.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
	String email,
	@Schema(description = "사용자 이름", example = "문지언")
	@NotBlank(message = "이름은 필수 입력값입니다.")
	@Size(min = 2, max = 20, message = "이름은 2자이상 50자 이하로 입력해야 합니다.")
	String name
) {
	public User toUser() {
		return User.createUser(name, email);
	}
}
