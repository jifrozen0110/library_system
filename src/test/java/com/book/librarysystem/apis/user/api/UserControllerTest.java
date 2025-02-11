package com.book.librarysystem.apis.user.api;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.book.librarysystem.apis.ControllerTestSupport;
import com.book.librarysystem.applications.user.request.UserJoinRequest;
import com.book.librarysystem.applications.user.response.UserResponse;
import com.book.librarysystem.fixtures.user.UserFixture;

@DisplayName("[컨트롤러] User API")
class UserControllerTest extends ControllerTestSupport {

	@Nested
	@DisplayName("회원가입 API")
	class JoinUser {

		@Test
		@DisplayName("성공: 회원가입을 하면 사용자 ID를 반환한다.")
		void success() throws Exception {
			UserJoinRequest request = UserFixture.userJoinRequest;
			given(userService.join(any(UserJoinRequest.class))).willReturn(1L);

			mockMvc.perform(post("/api/user/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
		}

		@Test
		@DisplayName("실패: 이메일이 null이면 예외가 발생한다.")
		void failJoinWhenEmailIsNull() throws Exception {
			UserJoinRequest request = UserFixture.nullEmailUserJoinRequest;

			mockMvc.perform(post("/api/user/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest());
		}

		@Test
		@DisplayName("실패: 이메일이 빈 문자열이면 예외가 발생한다.")
		void failJoinWhenEmailIsEmpty() throws Exception {
			UserJoinRequest request = UserFixture.emptyEmailUserJoinRequest;

			mockMvc.perform(post("/api/user/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest());
		}

		@Test
		@DisplayName("실패: 이름이 null이면 예외가 발생한다.")
		void failJoinWhenNameIsNull() throws Exception {
			UserJoinRequest request = UserFixture.nullNameUserJoinRequest;

			mockMvc.perform(post("/api/user/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest());
		}

		@Test
		@DisplayName("실패: 이름이 빈 문자열이면 예외가 발생한다.")
		void failJoinWhenNameIsEmpty() throws Exception {
			UserJoinRequest request = UserFixture.emptyNameUserJoinRequest;

			mockMvc.perform(post("/api/user/join")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isBadRequest());
		}
	}

	@Test
	@DisplayName("성공: ID로 사용자를 조회하면 정보를 반환한다.")
	void getUserById() throws Exception {
		UserResponse response = UserFixture.userResponse;

		given(userService.findById(1L)).willReturn(response);

		mockMvc.perform(get("/api/user/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(response.id()))
			.andExpect(jsonPath("$.email").value(response.email()))
			.andExpect(jsonPath("$.name").value(response.name()));
	}

	@Nested
	@DisplayName("사용자 목록 조회 API")
	class GetAllUsers {

		@Test
		@DisplayName("성공: 모든 사용자 목록을 반환한다.")
		void success() throws Exception {
			List<UserResponse> users = UserFixture.userResponses;
			given(userService.getAllUsers()).willReturn(users);

			mockMvc.perform(get("/api/user"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(2))
				.andExpect(jsonPath("$[0].email").value("answldjs1836@gmail.com"))
				.andExpect(jsonPath("$[1].email").value("answldjs0000@naver.com"));
		}
	}

}
