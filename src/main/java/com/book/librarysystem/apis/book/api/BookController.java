package com.book.librarysystem.apis.book.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.book.librarysystem.apis.book.spec.BookControllerSpec;
import com.book.librarysystem.applications.book.BookService;
import com.book.librarysystem.applications.book.request.BookDeleteRequest;
import com.book.librarysystem.applications.book.request.BookRegisterRequest;
import com.book.librarysystem.applications.book.request.BookUpdateRequest;
import com.book.librarysystem.applications.book.response.BookDetailResponse;
import com.book.librarysystem.applications.book.response.BookResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Book API", description = "책 정보를 관리하는 API")
@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController implements BookControllerSpec {

	private final BookService bookService;

	@Override
	@GetMapping
	public ResponseEntity<List<BookResponse>> getBooks() {
		List<BookResponse> books = bookService.getAllBooks();
		return ResponseEntity.ok(books);
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<BookDetailResponse> getBook(@PathVariable Long id) {
		BookDetailResponse book = bookService.findById(id);
		return ResponseEntity.ok(book);
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Long> createBook(@RequestBody @Valid BookRegisterRequest request) {
		Long bookId = bookService.registerBook(request);
		return ResponseEntity.ok(bookId);
	}

	@Override
	@PatchMapping("/{id}")
	public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody @Valid BookUpdateRequest request) {
		bookService.updateBook(id, request);
		return ResponseEntity.ok("책 정보가 수정되었습니다.");
	}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Long id, @RequestBody @Valid BookDeleteRequest request) {
		bookService.deleteBook(id, request);
		return ResponseEntity.ok("책 정보가 삭제되었습니다.");
	}
}
