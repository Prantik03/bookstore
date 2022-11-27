package com.bookstore.demo.service;

import com.bookstore.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface US {

    User createUser(User user);
    User suspendUser(User user);
    User updateUser(User user);

    User addMoney(User user);




}
