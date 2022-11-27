package com.bookstore.demo.service;


import com.bookstore.demo.model.rent;
import com.bookstore.demo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface GS {

    ResponseEntity<HttpStatus> borrowBook(int uId, int bookId);
    ResponseEntity<HttpStatus> reviewBook(int uId, int bookId, String review);


}
