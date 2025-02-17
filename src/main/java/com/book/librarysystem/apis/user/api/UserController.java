package com.book.librarysystem.apis.user.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.book.librarysystem.apis.user.spec.UserControllerSpec;
import com.book.librarysystem.applications.user.UserService;
import com.book.librarysystem.applications.user.request.UserJoinRequest;
import com.book.librarysystem.applications.user.response.UserResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "User API", description = "사용자 정보를 관리하는 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController implements UserControllerSpec {

	private final UserService userService;

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/join")
	public ResponseEntity<Long> join(@RequestBody @Valid UserJoinRequest request) {
		Long userId = userService.join(request);
		return ResponseEntity.ok(userId);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
		UserResponse userResponse = userService.findById(id);
		return ResponseEntity.ok(userResponse);
	}

	@Override
	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		List<UserResponse> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

}
