package com.book.librarysystem.globals.util;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.book.librarysystem.globals.exception.CustomDateFormatException;

@DisplayName("[유틸] DateTimeConverter")
class DateTimeConverterTest {

	@ParameterizedTest(name = "[{index}] 유효한 날짜 문자열 \"{0}\" 은 정상 변환된다.")
	@ValueSource(strings = {"2021-01-01", "2020-12-31", "1999-07-15"})
	@DisplayName("convert_ValidDateString_ReturnsLocalDate")
	void convert_ValidDateString_ReturnsLocalDate(String validDateStr) {
		LocalDate date = DateTimeConverter.parseDate(validDateStr);
		assertEquals(validDateStr, date.format(DateTimeConverter.DATE_FORMATTER));
	}

	@ParameterizedTest(name = "[{index}] 유효하지 않은 날짜 문자열 \"{0}\" 은 예외를 발생시킨다.")
	@ValueSource(strings = {"2021-13-01", "abc", "2021/01/01"})
	@DisplayName("fail_Convert_InvalidDateString_ThrowsIllegalArgumentException")
	void fail_Convert_InvalidDateString_ThrowsIllegalArgumentException(String invalidDateStr) {
		assertThatThrownBy(() -> DateTimeConverter.parseDate(invalidDateStr))
			.isInstanceOf(CustomDateFormatException.class)
			.hasMessageContaining("날짜 형식이 잘못되었습니다.");
	}

	@ParameterizedTest(name = "[{index}] 유효한 날짜/시간 문자열 \"{0}\" 은 정상 변환된다.")
	@CsvSource({
		"2021-01-01 12:30:45, 2021-01-01 12:30:45",
		"2020-05-05 00:00:00, 2020-05-05 00:00:00"
	})
	@DisplayName("convert_ValidDateTimeString_ReturnsLocalDateTime")
	void convert_ValidDateTimeString_ReturnsLocalDateTime(String dateTimeStr, String expectedStr) {
		LocalDateTime dateTime = DateTimeConverter.parseDateTime(dateTimeStr, DateTimeConverter.DATE_TIME_FORMATTER);
		String formatted = DateTimeConverter.formatDateTime(dateTime);
		assertEquals(expectedStr, formatted);
	}

	@ParameterizedTest(name = "[{index}] 유효하지 않은 날짜/시간 문자열 \"{0}\" 은 예외를 발생시킨다.")
	@ValueSource(strings = {"2021-01-01T12:30:45", "abc", "2021-01-01"})
	@DisplayName("fail_Convert_InvalidDateTimeString_ThrowsIllegalArgumentException")
	void fail_Convert_InvalidDateTimeString_ThrowsIllegalArgumentException(String invalidDateTimeStr) {
		assertThatThrownBy(
			() -> DateTimeConverter.parseDateTime(invalidDateTimeStr, DateTimeConverter.DATE_TIME_FORMATTER))
			.isInstanceOf(CustomDateFormatException.class)
			.hasMessageContaining("날짜 형식이 잘못되었습니다.");
	}

	@ParameterizedTest(name = "[{index}] LocalDate \"{0}\" 포맷 시 \"{1}\" 이 반환된다.")
	@CsvSource({
		"2021-01-01, 2021-01-01",
		"2020-05-05, 2020-05-05"
	})
	@DisplayName("formatDate_ValidLocalDate_ReturnsFormattedString")
	void formatDate_ValidLocalDate_ReturnsFormattedString(String dateStr, String expectedStr) {
		LocalDate date = LocalDate.parse(dateStr, DateTimeConverter.DATE_FORMATTER);
		String formatted = DateTimeConverter.formatDate(date);
		assertEquals(expectedStr, formatted);
	}

	@ParameterizedTest(name = "[{index}] null LocalDate 포맷 시 null 이 반환된다.")
	@NullSource
	@DisplayName("formatDate_NullLocalDate_ReturnsNull")
	void formatDate_NullLocalDate_ReturnsNull(LocalDate date) {
		assertNull(DateTimeConverter.formatDate(date));
	}

	@ParameterizedTest(name = "[{index}] LocalDateTime \"{0}\" 포맷 시 \"{1}\" 이 반환된다.")
	@CsvSource({
		"2021-01-01 12:30:45, 2021-01-01 12:30:45",
		"2020-05-05 00:00:00, 2020-05-05 00:00:00"
	})
	@DisplayName("formatDateTime_ValidLocalDateTime_ReturnsFormattedString")
	void formatDateTime_ValidLocalDateTime_ReturnsFormattedString(String dateTimeStr, String expectedStr) {
		LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, DateTimeConverter.DATE_TIME_FORMATTER);
		String formatted = DateTimeConverter.formatDateTime(dateTime);
		assertEquals(expectedStr, formatted);
	}

	@ParameterizedTest(name = "[{index}] null LocalDateTime 포맷 시 null 이 반환된다.")
	@NullSource
	@DisplayName("formatDateTime_NullLocalDateTime_ReturnsNull")
	void formatDateTime_NullLocalDateTime_ReturnsNull(LocalDateTime dateTime) {
		assertNull(DateTimeConverter.formatDateTime(dateTime));
	}
}
