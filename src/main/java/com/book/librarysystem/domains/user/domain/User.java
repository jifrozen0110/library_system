package com.book.librarysystem.domains.user.domain;

import com.book.librarysystem.domains.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@Embedded
	private Name name;

	@Embedded
	private Email email;

	@Enumerated(EnumType.STRING)
	private Role role = Role.MEMBER;

	private User(Name name, Email email) {
		this.name = name;
		this.email = email;
	}

	public static User createUser(String name, String email) {
		return new User(new Name(name), new Email(email));
	}
}
