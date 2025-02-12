package com.book.librarysystem.domains.user.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.book.librarysystem.domains.user.exception.UserNameBlankException;
import com.book.librarysystem.domains.user.exception.UserNameLengthException;

@DisplayName("[도메인] Name")
class NameTest {

	static Stream<String> invalidNames() {
		return Stream.of(null, "", " ");
	}

	@ParameterizedTest
	@MethodSource("invalidNames")
	@DisplayName("이름이 null, 빈 문자열 또는 공백일 경우 예외가 발생한다.")
	void failNameNullOrEmpty(String invalidName) {
		assertThatThrownBy(() -> new Name(invalidName))
			.isInstanceOf(UserNameBlankException.class)
			.hasMessage("이름은 null 이거나 공백일 수 없습니다.");
	}

	@Test
	@DisplayName("멤버 이름이 최대 길이(20자) 초과면 예외가 발생한다.")
	void failNameOverLength() {
		String overLengthName = "0".repeat(101);
		assertThatThrownBy(() -> new Name(overLengthName))
			.isInstanceOf(UserNameLengthException.class)
			.hasMessageContaining("멤버 이름의 길이가 최대 이름 길이를 초과했습니다. - 요청 정보 { 최대 길이 : 100, 입력한 길이 : 101 }");
	}

}
