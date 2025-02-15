package com.book.librarysystem.applications.book;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.book.librarysystem.applications.book.request.BookDeleteRequest;
import com.book.librarysystem.applications.book.request.BookRegisterRequest;
import com.book.librarysystem.applications.book.request.BookUpdateRequest;
import com.book.librarysystem.applications.book.response.BookResponse;
import com.book.librarysystem.domains.book.domain.Book;
import com.book.librarysystem.domains.book.exception.BookNotFoundException;
import com.book.librarysystem.domains.book.repository.BookRepository;
import com.book.librarysystem.globals.util.DateTimeConverter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;

	@Transactional
	@CacheEvict(cacheNames = "books", allEntries = true)
	public Long registerBook(BookRegisterRequest request) {
		Book book = request.toBook();
		book = bookRepository.save(book);
		return book.getId();
	}

	@Transactional
	@CacheEvict(cacheNames = "books", allEntries = true)
	public void updateBook(Long id, BookUpdateRequest request) {
		Book book = getBook(id);
		book.updateBook(request.title(), request.author(), DateTimeConverter.parseDate(request.publicationDate()),
			request.modifiedBy());
		bookRepository.save(book);
	}

	@Transactional
	@CacheEvict(cacheNames = "books", allEntries = true)
	public void deleteBook(Long id, BookDeleteRequest request) {
		Book book = getBook(id);
		book.deleteBook(request.deleteBy());
		bookRepository.save(book);
	}

	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "books", key = "'all'")
	public List<BookResponse> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		return books.stream()
			.map(BookResponse::from)
			.collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Cacheable(cacheNames = "books", key = "#id")
	public BookResponse findById(Long id) {
		return BookResponse.from(getBook(id));
	}

	public Book getBook(Long id) {
		return bookRepository.findById(id)
			.orElseThrow(() -> new BookNotFoundException());
	}

}
