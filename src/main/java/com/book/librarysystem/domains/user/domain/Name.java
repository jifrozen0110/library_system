package com.book.librarysystem.domains.user.domain;

import java.util.Objects;

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
public class Name {
	public static final int MAX_LENGTH = 100;

	@Column(name = "name", nullable = false, length = MAX_LENGTH)
	private String value;

	public Name(String value) {
		validate(value);
		this.value = value;
	}

	private void validate(String value) {
		if (Objects.isNull(value) || value.trim().isEmpty()) {
			throw new IllegalArgumentException("이름은 null 이거나 공백일 수 없습니다.");
		}
		if (value.length() > MAX_LENGTH) {
			throw new IllegalArgumentException("이름은 " + MAX_LENGTH + "자 이하여야 합니다.");
		}
	}
}
