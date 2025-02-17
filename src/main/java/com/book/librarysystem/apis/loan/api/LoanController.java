package com.book.librarysystem.apis.loan.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.book.librarysystem.apis.loan.spec.LoanControllerSpec;
import com.book.librarysystem.applications.loan.LoanService;
import com.book.librarysystem.applications.loan.request.LoanRegisterRequest;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "Loan API", description = "도서 대출 및 반납을 관리하는 API")
@RestController
@RequestMapping("/api/loan")
@RequiredArgsConstructor
public class LoanController implements LoanControllerSpec {

	private final LoanService loanService;

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<Long> registerLoan(@RequestBody @Valid LoanRegisterRequest request) {
		Long loanId = loanService.createdLoan(request);
		return ResponseEntity.ok(loanId);
	}

	@Override
	@GetMapping("/status/{bookId}")
	public ResponseEntity<Boolean> checkLoanStatus(@PathVariable Long bookId) {
		boolean isLoaned = loanService.isBookLoaned(bookId);
		return ResponseEntity.ok(isLoaned);
	}

	@Override
	@PatchMapping("/{loanId}/return")
	public ResponseEntity<String> returnLoan(@PathVariable Long loanId) {
		loanService.returnLoan(loanId);
		return ResponseEntity.ok("도서 반납이 완료되었습니다.");
	}
}
