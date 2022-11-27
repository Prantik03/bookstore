package com.bookstore.demo.repository;

import com.bookstore.demo.model.User;
import com.bookstore.demo.model.book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<User,Long> {
}
