package com.book.librarysystem.domains.user.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.book.librarysystem.domains.user.exception.UserEmailBlankException;
import com.book.librarysystem.domains.user.exception.UserEmailInvalidException;

@DisplayName("[도메인] Email")
class EmailTest {

	static Stream<String> invalidEmails() {
		return Stream.of(null, "", " ");
	}

	@ParameterizedTest
	@MethodSource("invalidEmails")
	@DisplayName("이메일이 null, 빈 문자열 또는 공백일 경우 예외가 발생한다.")
	void failEmailNullOrEmpty(String invalidEmail) {
		assertThatThrownBy(() -> new Email(invalidEmail))
			.isInstanceOf(UserEmailBlankException.class)
			.hasMessage("이메일은 null 이거나 공백일 수 없습니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"aaaagmail.com", "aaa@gmailcom", "aaagmailcom"})
	@DisplayName("이메일이 양식을 만족하지 않으면 예외가 발생한다.")
	void failEmailInValidFormat(String value) {
		// when & then
		Assertions.assertThatThrownBy(() -> new Email(value))
			.isInstanceOf(UserEmailInvalidException.class)
			.hasMessageContaining("이메일 형식이 올바르지 않습니다");
	}

}
