package com.book.librarysystem.domains.user.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("[도메인] Name")
class NameTest {

	static Stream<String> invalidNames() {
		return Stream.of(null, "", " ");
	}

	@ParameterizedTest
	@MethodSource("invalidNames")
	@DisplayName("이메일이 null, 빈 문자열 또는 공백일 경우 예외가 발생한다.")
	void nullOrEmptyEmailThenIllegalArgumentException(String invalidEmail) {
		assertThatThrownBy(() -> new Email(invalidEmail))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이메일은 null 이거나 공백일 수 없습니다.");
	}

	@Test
	@DisplayName("멤버 이름이 최대 길이(20자) 초과면 예외가 발생한다.")
	void nameOverMaxLengthThenIllegalArgumentException() {
		String overLengthName = "0".repeat(101);
		assertThatThrownBy(() -> new Name(overLengthName))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이름은 100자 이하여야 합니다.");
	}

}
