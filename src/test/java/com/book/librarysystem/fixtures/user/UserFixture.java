package com.book.librarysystem.fixtures.user;

import java.util.List;

import com.book.librarysystem.applications.user.request.UserJoinRequest;
import com.book.librarysystem.applications.user.response.UserResponse;

public class UserFixture {
	public static final UserJoinRequest userJoinRequest = new UserJoinRequest("answldjs1836@gmail.com", "문지언");
	public static final UserJoinRequest userJoinRequest2 = new UserJoinRequest("answldjs0000@naver.com", "문지연");
	public static final UserJoinRequest nullEmailUserJoinRequest = new UserJoinRequest(null, "문지언");
	public static final UserJoinRequest emptyEmailUserJoinRequest = new UserJoinRequest("", "문지언");
	public static final UserJoinRequest nullNameUserJoinRequest = new UserJoinRequest("answldjs1836@gmail.com", null);
	public static final UserJoinRequest emptyNameUserJoinRequest = new UserJoinRequest("answldjs1836@gmail.com", "");
	public static final UserResponse userResponse = new UserResponse(1L, "answldjs1836@gmail.com", "문지언");

	public static final List<UserResponse> userResponses = List.of(
		new UserResponse(1L, "answldjs1836@gmail.com", "문지언"),
		new UserResponse(2L, "answldjs0000@naver.com", "문지연"));
}
