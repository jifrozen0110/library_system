package com.book.librarysystem.apis.loan.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.book.librarysystem.apis.ControllerTestSupport;
import com.book.librarysystem.applications.loan.request.LoanRegisterRequest;
import com.book.librarysystem.fixtures.book.BookFixture;
import com.book.librarysystem.fixtures.loan.LoanFixture;
import com.book.librarysystem.fixtures.user.UserFixture;

@DisplayName("[컨트롤러] Loan API")
class LoanControllerTest extends ControllerTestSupport {

	@Test
	@DisplayName("성공: 대출을 등록하면 대출 ID를 반환한다.")
	void RegisterLoan() throws Exception{
		LoanRegisterRequest request = LoanFixture.loanRegisterRequest;
		given(loanService.createdLoan(any(LoanRegisterRequest.class))).willReturn(1L);

		mockMvc.perform(post("/api/loan")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk())
			.andExpect(content().string("1"));

	}

	@Test
	@DisplayName("성공: 대출을 반납합니다.")
	void returnLoan() throws Exception {
		Long loanId = 1L;
		willDoNothing().given(loanService).returnLoan(loanId);

		mockMvc.perform(patch("/api/loan/{id}/return", 1L))
			.andExpect(status().isOk())
			.andExpect(content().string("도서 반납이 완료되었습니다."));
	}

	@Test
	@DisplayName("성공: 등록된 대출이 있으면 대출 상태 확인 시 true를 반환한다.")
	void checkLoanStatus() throws Exception {
		Long bookId = 1L;
		given(loanService.isBookLoaned(bookId)).willReturn(true);

		mockMvc.perform(get("/api/loan/status/{bookId}",bookId))
			.andExpect(status().isOk())
			.andExpect(content().string("true"));
	}

}
