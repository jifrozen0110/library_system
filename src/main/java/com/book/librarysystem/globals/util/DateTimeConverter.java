package com.book.librarysystem.globals.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.book.librarysystem.globals.exception.CustomDateFormatException;

public final class DateTimeConverter {
	public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private DateTimeConverter() {
	}

	public static LocalDate parseDate(String dateStr) {
		try {
			LocalDate date = LocalDate.parse(dateStr, DATE_FORMATTER);
			return date;
		} catch (DateTimeParseException e) {
			throw new CustomDateFormatException(e);
		}
	}

	public static LocalDateTime parseDateTime(String dateTimeStr, DateTimeFormatter formatter) {
		try {
			return LocalDateTime.parse(dateTimeStr, formatter);
		} catch (DateTimeParseException e) {
			throw new CustomDateFormatException(e);
		}
	}

	public static String formatDate(LocalDate date) {
		return date != null ? date.format(DATE_FORMATTER) : null;
	}

	public static String formatDateTime(LocalDateTime dateTime) {
		return dateTime != null ? dateTime.format(DATE_TIME_FORMATTER) : null;
	}
}
