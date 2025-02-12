package com.book.librarysystem.applications.user;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.librarysystem.applications.user.request.UserJoinRequest;
import com.book.librarysystem.applications.user.response.UserResponse;
import com.book.librarysystem.domains.user.domain.Email;
import com.book.librarysystem.domains.user.domain.User;
import com.book.librarysystem.domains.user.exception.UserNotFoundException;
import com.book.librarysystem.domains.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public Long join(UserJoinRequest request) {
		User user = userRepository.findByEmail(new Email(request.email()))
			.orElseGet(() -> userRepository.save(request.toUser()));

		return user.getId();
	}

	@Transactional(readOnly = true)
	public UserResponse findById(Long Id) {
		User user = getUser(Id);
		return UserResponse.of(user);
	}

	@Transactional(readOnly = true)
	public List<UserResponse> getAllUsers() {
		return userRepository.findAll().stream().map(UserResponse::of).toList();
	}

	public User getUser(Long Id) {
		return userRepository.findById(Id)
			.orElseThrow(() -> new UserNotFoundException());
	}

}
