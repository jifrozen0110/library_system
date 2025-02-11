package com.book.librarysystem.apis.test.spec;

import java.util.List;

import com.book.librarysystem.apis.test.dto.TestRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Test API", description = "테스트 API 컨트롤러")
public interface TestControllerSpec {

	@Operation(summary = "모든 테스트 데이터 조회", description = "등록된 모든 테스트 데이터를 조회합니다.")
	List<String> getAllTestData();

	@Operation(summary = "테스트 데이터 추가", description = "새로운 테스트 데이터를 등록합니다.")
	String createTestData(TestRequest data);

	@Operation(summary = "특정 테스트 데이터 조회", description = "ID를 통해 특정 테스트 데이터를 조회합니다.")
	String getTestDataById(Long id);

	@Operation(summary = "테스트 데이터 삭제", description = "모든 테스트 데이터를 삭제합니다.")
	void resetTestData();
}
