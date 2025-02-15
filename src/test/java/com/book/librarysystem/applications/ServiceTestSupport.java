package com.book.librarysystem.applications;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import com.book.librarysystem.globals.config.RedisTestConfig;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Import(RedisTestConfig.class)
@Transactional
@Sql("/truncate.sql")
public abstract class ServiceTestSupport {
}
