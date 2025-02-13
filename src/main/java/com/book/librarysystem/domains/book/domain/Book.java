package com.book.librarysystem.domains.book.domain;

import java.time.LocalDate;
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

	private LocalDate publishedAt;

	private String modifiedBy;
	private String deletedBy;

	private LocalDateTime deletedAt;

	private Book(String title, String author, LocalDate publishedAt) {
		this.title = title;
		this.author = author;
		this.publishedAt = publishedAt;
	}

	public static Book createBook(String title, String author, LocalDate publishedAt) {
		return new Book(title, author, publishedAt);
	}

	public void updateBook(String title, String author, LocalDate publishedAt, String modifiedBy) {
		this.title = title;
		this.author = author;
		this.publishedAt = publishedAt;
		this.modifiedBy = modifiedBy;
	}

	public void deleteBook(String deletedBy) {
		this.deletedBy = deletedBy;
		this.deletedAt = LocalDateTime.now();
	}

}
