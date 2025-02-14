package com.book.librarysystem.applications.book;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.book.librarysystem.applications.ServiceTestSupport;
import com.book.librarysystem.applications.book.request.BookDeleteRequest;
import com.book.librarysystem.applications.book.request.BookRegisterRequest;
import com.book.librarysystem.applications.book.request.BookUpdateRequest;
import com.book.librarysystem.applications.book.response.BookResponse;
import com.book.librarysystem.domains.book.domain.Book;
import com.book.librarysystem.domains.book.exception.BookNotFoundException;
import com.book.librarysystem.domains.book.repository.BookRepository;
import com.book.librarysystem.fixtures.book.BookFixture;
import com.book.librarysystem.globals.util.DateTimeConverter;

@DisplayName("[서비스] Book Service")
class BookServiceTest extends ServiceTestSupport {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@Test
	@DisplayName("등록 성공")
	void registerBook() {
		BookRegisterRequest request = BookFixture.bookRegisterRequest;
		Long bookId = bookService.registerBook(request);

		assertThat(bookId).isNotNull();
		assertThat(bookRepository.findById(bookId)).isPresent();
	}

	@Test
	@DisplayName("수정 성공")
	void updateBook() {
		BookRegisterRequest registerRequest = BookFixture.bookRegisterRequest;
		Long bookId = bookService.registerBook(registerRequest);

		BookUpdateRequest updateRequest = BookFixture.bookUpdateRequest;
		bookService.updateBook(bookId, updateRequest);

		BookResponse response = bookService.findById(bookId);
		assertThat(response.title()).isEqualTo(updateRequest.title());
		assertThat(response.author()).isEqualTo(updateRequest.author());
		String expectedPublicationDate = DateTimeConverter.formatDate(
			LocalDate.parse(updateRequest.publicationDate(), DateTimeConverter.DATE_FORMATTER)
		);
		assertThat(response.publicationDate()).isEqualTo(expectedPublicationDate);
	}

	@Test
	@DisplayName("삭제 성공")
	void deleteBook() {
		BookRegisterRequest request = BookFixture.bookRegisterRequest;
		Long bookId = bookService.registerBook(request);

		BookDeleteRequest deleteRequest = BookFixture.bookDeleteRequest;
		bookService.deleteBook(bookId, deleteRequest);

		Book deletedBook = bookRepository.findById(bookId)
			.orElseThrow(() -> new IllegalStateException("도서가 존재하지 않습니다."));
		assertThat(deletedBook.getDeletedBy()).isEqualTo(deleteRequest.deleteBy());
		assertThat(deletedBook.getDeletedAt()).isNotNull();
	}

	@Nested
	@DisplayName("도서 조회 테스트")
	class FindBook {

		@Test
		@DisplayName("ID로 도서를 조회하면 BookResponse를 반환한다.")
		void success() {
			BookRegisterRequest request = BookFixture.bookRegisterRequest;
			Long bookId = bookService.registerBook(request);

			BookResponse response = bookService.findById(bookId);
			assertThat(response).extracting(BookResponse::id, BookResponse::title, BookResponse::author)
				.containsExactly(bookId, request.title(), request.author());
		}

		@Test
		@DisplayName("존재하지 않는 도서이면 BookNotFoundException을 던진다.")
		void notExistBookThenThrowException() {
			assertThatThrownBy(() -> bookService.getBook(999L))
				.isInstanceOf(BookNotFoundException.class)
				.hasMessage("책을 찾을 수 없습니다.");
		}
	}

	@Test
	@DisplayName("모든 도서를 조회하면 BookResponse 목록을 반환")
	void getAllBooks() {
		BookRegisterRequest request1 = BookFixture.bookRegisterRequest;
		BookRegisterRequest request2 = BookFixture.bookRegisterRequest2;

		bookService.registerBook(request1);
		bookService.registerBook(request2);

		assertThat(bookService.getAllBooks()).hasSize(2)
			.extracting(BookResponse::title, BookResponse::author)
			.containsExactlyInAnyOrder(
				tuple(request1.title(), request1.author()),
				tuple(request2.title(), request2.author())
			);
	}
}
