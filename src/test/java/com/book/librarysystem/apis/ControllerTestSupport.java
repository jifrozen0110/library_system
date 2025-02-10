package com.book.librarysystem.apis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.book.librarysystem.apis.test.api.TestController;

@WebMvcTest(
	controllers = {
		TestController.class,
	},
	excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {WebMvcConfigurer.class})}
)
public abstract class ControllerTestSupport {
	@Autowired
	protected MockMvc mockMvc;
}
