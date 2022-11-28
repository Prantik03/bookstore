package com.bookstore.demo.repository;


import com.bookstore.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface reviewRepo extends JpaRepository<Review,Integer> {
}
