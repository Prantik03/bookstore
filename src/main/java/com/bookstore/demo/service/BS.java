package com.bookstore.demo.service;
import com.bookstore.demo.model.book;
import com.bookstore.demo.model.BookCopies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BS {
    book addBook(book book);

    ResponseEntity<HttpStatus> addCopies(BookCopies bc);
}
