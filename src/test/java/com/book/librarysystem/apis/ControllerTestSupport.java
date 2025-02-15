package com.book.librarysystem.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.book.librarysystem.apis.book.api.BookController;
import com.book.librarysystem.apis.loan.api.LoanController;
import com.book.librarysystem.apis.test.api.TestController;
import com.book.librarysystem.apis.user.api.UserController;
import com.book.librarysystem.applications.book.BookService;
import com.book.librarysystem.applications.loan.LoanService;
import com.book.librarysystem.applications.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(
	controllers = {
		TestController.class,
		UserController.class,
		BookController.class,
		LoanController.class
	},
	excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebMvcConfigurer.class})}
)
public abstract class ControllerTestSupport {
	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@MockitoBean
	protected UserService userService;

	@MockitoBean
	protected BookService bookService;

	@MockitoBean
	protected LoanService loanService;
}
