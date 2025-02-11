package com.book.librarysystem.domains.user.domain;

import java.util.Objects;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Email {

	private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

	@Column(name = "email", nullable = false, unique = true)
	private String value;

	public Email(String value) {
		validate(value);
		this.value = value;
	}

	private void validate(String value) {
		if (Objects.isNull(value) || value.trim().isEmpty()) {
			throw new IllegalArgumentException("이메일은 null 이거나 공백일 수 없습니다.");
		}

		if (isNotMatchEmailForm(value)) {
			throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다: " + value);
		}
	}

	private boolean isNotMatchEmailForm(String value) {
		return !Pattern.matches(EMAIL_REGEX, value);
	}
}
