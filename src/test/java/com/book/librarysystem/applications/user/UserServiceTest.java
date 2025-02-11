package com.book.librarysystem.applications.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.book.librarysystem.applications.ServiceTestSupport;
import com.book.librarysystem.applications.user.request.UserJoinRequest;
import com.book.librarysystem.applications.user.response.UserResponse;
import com.book.librarysystem.domains.user.repository.UserRepository;
import com.book.librarysystem.fixtures.user.UserFixture;

@DisplayName("[서비스] User Service")
class UserServiceTest extends ServiceTestSupport {
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Nested
	@DisplayName("사용자 등록 테스트")
	class JoinUser {
		@Test
		@DisplayName("등록 성공")
		void success() {
			UserJoinRequest request = UserFixture.userJoinRequest;

			Long userId = userService.join(request);

			assertThat(userId).isNotNull();
			assertThat(userRepository.findById(userId)).isPresent();
		}

		@Test
		@DisplayName("이미 등록된 email이면 가존 회원 번호가 반환된다.")
		void joinExistingUserReturnUserId() {
			UserJoinRequest request = UserFixture.userJoinRequest;

			Long userId = userService.join(request);
			Long existingUserId = userService.join(request);

			assertThat(userId).isEqualTo(existingUserId);
		}
	}

	@Nested
	@DisplayName("사용자 조회 테스트")
	class FindUser {
		@Test
		@DisplayName("Id로 사영자를 조회하면 UserResponse를 반환한다.")
		void success() {
			UserJoinRequest request = UserFixture.userJoinRequest;
			Long userId = userService.join(request);

			UserResponse userResponse = userService.findById(userId);

			assertThat(userResponse).extracting(UserResponse::id, UserResponse::email, UserResponse::name)
				.containsExactly(userId, request.email(), request.name());
		}

		@Test
		@DisplayName("사용자가 없으면 IllegalArgumentException을 던진다.")
		void notExistUserThenThrowIllegalArgumentException() {
			assertThatThrownBy(() -> userService.getUser(999L))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("사용자를 찾을 수 없습니다.");
		}
	}

	@Test
	@DisplayName("모든 사용자를 조회하면 UserResponse 목록을 반환한다.")
	void getAllUsers() {
		UserJoinRequest request1 = UserFixture.userJoinRequest;
		UserJoinRequest request2 = UserFixture.userJoinRequest2;

		userService.join(request1);
		userService.join(request2);

		assertThat(userService.getAllUsers()).hasSize(2)
			.extracting(UserResponse::id, UserResponse::email, UserResponse::name)
			.containsExactlyInAnyOrder(
				tuple(1L, request1.email(), request1.name()),
				tuple(2L, request2.email(), request2.name())
			);
	}
}
