package com.book.librarysystem.applications.loan.request;

import com.book.librarysystem.domains.loan.domain.Loan;
import com.book.librarysystem.globals.util.DateTimeConverter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "대출 등록 요청 Request")
public record LoanRegisterRequest(
	@Schema(description = "대출일", example = "2023-05-01")
	@NotNull(message = "대출일은 필수입니다.")
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "대출일은 yyyy-MM-dd 형식이어야 합니다.")
	String loanDate,

	@NotNull(message = "도서 ID는 필수입니다.")
	@Schema(description = "도서 ID", example = "1")
	Long bookId,

	@NotNull(message = "사용자 ID는 필수입니다.")
	@Schema(description = "사용자 ID", example = "1")
	Long userId
) {
	public Loan toLoan() {
		return Loan.createLoan(DateTimeConverter.parseDate(loanDate), bookId, userId);
	}
}
