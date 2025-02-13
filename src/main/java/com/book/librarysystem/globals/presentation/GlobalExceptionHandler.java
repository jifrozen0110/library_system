package com.book.librarysystem.globals.presentation;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.book.librarysystem.globals.exception.CustomBadRequestException;
import com.book.librarysystem.globals.exception.CustomNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e
	) {
		String defaultErrorMessage = Optional.ofNullable(e.getBindingResult().getAllErrors().stream()
				.findFirst()
				.orElse(null))
			.map(error -> error.getDefaultMessage())
			.orElse("잘못된 요청입니다.");
		log.warn("[MethodArgumentNotValidException] {}", defaultErrorMessage);

		return ResponseEntity.badRequest().body(new ErrorResponse(defaultErrorMessage));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException e
	) {
		String errorMessage = String.format("'%s' 값이 '%s' 타입에 맞지 않습니다.", e.getValue(),
			e.getRequiredType().getSimpleName());
		log.warn("[MethodArgumentTypeMismatchException] {}", errorMessage);

		return ResponseEntity.badRequest().body(new ErrorResponse(errorMessage));
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
		HttpMessageNotReadableException e
	) {
		log.warn("[HttpMessageNotReadableException] {}", e.getMessage());

		return ResponseEntity
			.badRequest()
			.body(new ErrorResponse("요청 내용을 읽을 수 없습니다. 형식을 확인하세요."));
	}

	@ExceptionHandler(CustomNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCustomNotFoundException(final CustomNotFoundException e) {
		log.warn("[CustomNotFoundException] {}", e.getMessage());

		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ErrorResponse(e.getMessage()));
	}

	@ExceptionHandler(CustomBadRequestException.class)
	public ResponseEntity<ErrorResponse> handleCustomBadRequestException(final CustomBadRequestException e) {
		log.warn("[CustomBadRequestException] {}", e.getMessage());

		return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(new ErrorResponse(e.getMessage()));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(final RuntimeException e) {
		log.error("[RuntimeException] ", e);

		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(new ErrorResponse("서버 오류가 발생했습니다. 관리자에게 문의하세요."));
	}
}
