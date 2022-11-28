package com.bookstore.demo.controller;
import com.bookstore.demo.service.GS;
import com.bookstore.demo.model.User;
import com.bookstore.demo.model.rent;
import com.bookstore.demo.model.book;
//import com.bookstore.demo.repository.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GeneralController {

    @Autowired
    private GS generalService;

    @PutMapping("/users/rent/{uId}/{bookId}")
    private ResponseEntity<HttpStatus> borrowBook(@PathVariable int uId, @PathVariable int bookId){
        return generalService.borrowBook(uId,bookId);
    }

    @PutMapping("/books/return/{rentedId}")
    private ResponseEntity<HttpStatus> returnBook(@PathVariable int rentedId){
        return generalService.returnBook(rentedId);
    }

    @PostMapping("/books/review/{uId}/{bookId}")
    private ResponseEntity<HttpStatus> reviewBook(@PathVariable int uId,@PathVariable int bookId, @RequestBody String review){
        return generalService.reviewBook(uId,bookId,review);
    }

    @PostMapping("/books/like/{uId}/{bookId}")
    private ResponseEntity<HttpStatus> likeBook(@PathVariable int uId, @PathVariable int bookId){
        return generalService.likeBook(uId,bookId);
    }

    @GetMapping("/books")
    private ResponseEntity<List<book>> availableBooks() {
        //return ResponseEntity.ok().body(this.productService.getProd());
        return null;
    }

//    @GetMapping("/user/transactions/{userId}")
//    private ResponseEntity<List<Rented>> showTransactions(@PathVariable int userId) {
//        //return ResponseEntity.ok().body(this.productService.getProd());
//        return rentedRepository.showTransactions(userId);
//    }

}

