package com.book.librarysystem.apis.test.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.librarysystem.apis.test.dto.TestRequest;
import com.book.librarysystem.apis.test.spec.TestControllerSpec;

import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/test")
public class TestController implements TestControllerSpec {

	private final List<String> testData = new ArrayList<>();

	@Override
	@GetMapping
	public List<String> getAllTestData() {
		return testData;
	}

	@Override
	@PostMapping
	public String createTestData(@RequestBody @Schema(implementation = TestRequest.class) TestRequest data) {
		testData.add(data.getData());
		return "테스트 데이터를 추가합니다 : " + data.getData();
	}

	@Override
	@GetMapping("/{id}")
	public String getTestDataById(@PathVariable Long id) {
		if (id < 0 || id >= testData.size()) {
			return "해당 ID의 데이터가 존재하지 않습니다.";
		}
		return testData.get(id.intValue());
	}

	@Override
	@DeleteMapping
	public void resetTestData() {
		testData.clear();
	}

}
