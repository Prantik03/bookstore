package com.bookstore.demo.repository;

import com.bookstore.demo.model.book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface bookRepo extends JpaRepository<book,Long>{

}
