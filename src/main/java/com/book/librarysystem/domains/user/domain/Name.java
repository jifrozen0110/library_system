package com.book.librarysystem.domains.user.domain;

import java.util.Objects;

import com.book.librarysystem.domains.user.exception.UserNameBlankException;
import com.book.librarysystem.domains.user.exception.UserNameLengthException;

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
			throw new UserNameBlankException();
		}
		if (value.length() > MAX_LENGTH) {
			throw new UserNameLengthException(MAX_LENGTH, value.length());
		}
	}
}
