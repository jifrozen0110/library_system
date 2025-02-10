package com.book.librarysystem.domains.book.domain;

import java.time.LocalDateTime;

import com.book.librarysystem.domains.common.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_book")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;

	@Column(length = 255, nullable = false)
	private String title;

	@Column(length = 100, nullable = false)
	private String author;

	private LocalDateTime publishedAt;

	private String modifiedBy;
	private String deletedBy;

	private LocalDateTime deletedAt;

}
