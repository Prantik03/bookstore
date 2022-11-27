package com.bookstore.demo.repository;

import com.bookstore.demo.model.rent;
import org.springframework.data.jpa.repository.JpaRepository;

//RENTED TABLE
public interface genRepo extends JpaRepository<rent,Integer> {
}

