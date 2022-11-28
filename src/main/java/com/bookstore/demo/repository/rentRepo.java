package com.bookstore.demo.repository;

import com.bookstore.demo.model.rent;
import com.bookstore.demo.model.rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface rentRepo extends JpaRepository<rent,Integer> {

}