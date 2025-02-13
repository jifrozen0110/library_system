package com.book.librarysystem.domains.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.librarysystem.domains.book.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
