package com.book.librarysystem.apis.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "테스트 요청 데이터")
public class TestRequest {
	@Schema(description = "테스트 데이터")
	private String data;
}
