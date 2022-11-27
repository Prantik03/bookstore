package com.bookstore.demo.repository;

import com.bookstore.demo.model.BookCopies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bookcopyRepo extends JpaRepository<BookCopies, Integer> {
}
