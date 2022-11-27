package com.bookstore.demo.controller;

import com.bookstore.demo.model.book;
import com.bookstore.demo.model.BookCopies;
import com.bookstore.demo.service.BS;
import com.bookstore.demo.service.GS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
public class BookController {

    @Autowired
    private BS bookService;

    @Autowired
    private GS generalService;

    @PostMapping("/books/add")
    private ResponseEntity<book> addBook(@RequestBody book book) {
        return ResponseEntity.ok().body(this.bookService.addBook(book));
    }

    @PostMapping("/books/addcopies")
    private ResponseEntity<HttpStatus> addCopies( @RequestBody BookCopies bc){
        //bc.setbId(bId);
        bookService.addCopies(bc);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
