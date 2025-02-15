package com.book.librarysystem.apis.book.api;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.book.librarysystem.apis.ControllerTestSupport;
import com.book.librarysystem.applications.book.request.BookDeleteRequest;
import com.book.librarysystem.applications.book.request.BookRegisterRequest;
import com.book.librarysystem.applications.book.request.BookUpdateRequest;
import com.book.librarysystem.applications.book.response.BookDetailResponse;
import com.book.librarysystem.applications.book.response.BookResponse;
import com.book.librarysystem.fixtures.book.BookFixture;

@DisplayName("[컨트롤러] Book API")
class BookControllerTest extends ControllerTestSupport {

	@Nested
	@DisplayName("도서 등록 API")
	class CreateBook {

		@Test
		@DisplayName("성공: 도서를 등록하면 도서 ID를 반환한다.")
		void success() throws Exception {
			BookRegisterRequest request = BookFixture.bookRegisterRequest;
			given(bookService.registerBook(any(BookRegisterRequest.class))).willReturn(1L);

			mockMvc.perform(post("/api/book")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(content().string("1"));
		}
	}

	@Nested
	@DisplayName("도서 수정 API")
	class UpdateBook {

		@Test
		@DisplayName("성공: 도서를 수정하면 '책 정보가 수정되었습니다.' 메시지를 반환한다.")
		void success() throws Exception {
			BookUpdateRequest request = BookFixture.bookUpdateRequest;
			willDoNothing().given(bookService).updateBook(eq(1L), any(BookUpdateRequest.class));

			mockMvc.perform(patch("/api/book/{id}", 1L)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(content().string("책 정보가 수정되었습니다."));
		}
	}

	@Nested
	@DisplayName("도서 삭제 API")
	class DeleteBook {

		@Test
		@DisplayName("성공: 도서를 삭제하면 '책 정보가 삭제되었습니다.' 메시지를 반환한다.")
		void success() throws Exception {
			BookDeleteRequest request = BookFixture.bookDeleteRequest;
			willDoNothing().given(bookService).deleteBook(eq(1L), any(BookDeleteRequest.class));

			mockMvc.perform(delete("/api/book/{id}", 1L)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(content().string("책 정보가 삭제되었습니다."));
		}
	}

	@Test
	@DisplayName("성공: ID로 도서를 조회하면 정보를 반환한다.")
	void getBookById() throws Exception {
		BookDetailResponse response = BookFixture.bookDetailResponse;
		given(bookService.findById(1L)).willReturn(response);

		mockMvc.perform(get("/api/book/{id}", 1L))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(response.id()))
			.andExpect(jsonPath("$.title").value(response.title()))
			.andExpect(jsonPath("$.author").value(response.author()))
			.andExpect(jsonPath("$.publicationDate").value(response.publicationDate()));
	}

	@Nested
	@DisplayName("도서 목록 조회 API")
	class GetAllBooks {

		@Test
		@DisplayName("성공: 모든 도서 목록을 반환한다.")
		void success() throws Exception {
			List<BookResponse> responses = BookFixture.bookResponses;
			given(bookService.getAllBooks()).willReturn(responses);

			mockMvc.perform(get("/api/book"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(responses.size()))
				.andExpect(jsonPath("$[0].title").value(BookFixture.bookRegisterRequest.title()))
				.andExpect(jsonPath("$[1].title").value(BookFixture.bookRegisterRequest2.title()));
		}
	}
}
