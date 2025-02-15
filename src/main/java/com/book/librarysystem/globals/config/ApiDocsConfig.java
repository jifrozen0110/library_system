package com.book.librarysystem.globals.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class ApiDocsConfig {
	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
			.title("Library System API")
			.version("v.0.0.1")
			.description("도서 관리 시스템 API 명세서입니다.");
		return new OpenAPI().components(new Components()).info(info);

	}
}
