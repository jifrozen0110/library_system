package com.book.librarysystem.domains.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.librarysystem.domains.user.domain.Email;
import com.book.librarysystem.domains.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(Email email);
}
