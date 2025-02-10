package com.book.librarysystem.apis.test.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.book.librarysystem.apis.ControllerTestSupport;

@DisplayName("[컨트롤러] Test API")
class TestControllerTest extends ControllerTestSupport {

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc.perform(delete("/api/test"))
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("모든 테스트 데이터 조회")
	void testGetAllTestData() throws Exception {
		mockMvc.perform(get("/api/test"))
			.andExpect(status().isOk())
			.andExpect(content().string("[]"));
	}

	@Test
	@DisplayName("테스트 데이터 추가")
	void testCreateTestData() throws Exception {
		String request = "{\"data\":\"test\"}";

		mockMvc.perform(post("/api/test")
				.contentType("application/json")
				.content(request))
			.andExpect(status().isOk())
			.andExpect(content().string("테스트 데이터를 추가합니다 : test"));
	}

	@Test
	@DisplayName("특정 테스트 데이터 조회 - 존재하는 데이터")
	void testGetTestDataById() throws Exception {
		mockMvc.perform(post("/api/test")
				.contentType("application/json")
				.content("{\"data\":\"test\"}"))
			.andExpect(status().isOk());

		mockMvc.perform(get("/api/test/0"))
			.andExpect(status().isOk())
			.andExpect(content().string("test"));
	}

	@Test
	@DisplayName("특정 테스트 데이터 조회 - 존재하지 않는 데이터")
	void testGetTestDataById_NotExist() throws Exception {
		mockMvc.perform(get("/api/test/1"))
			.andExpect(status().isOk())
			.andExpect(content().string("해당 ID의 데이터가 존재하지 않습니다."));
	}

}
