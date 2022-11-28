package com.bookstore.demo.controller;

import com.bookstore.demo.model.User;
import com.bookstore.demo.service.userServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    @Autowired
    private userServices userService;

    @PostMapping("/users/add")
    private ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok().body(this.userService.createUser(user));
    }

    @PutMapping("/users/suspend/{uId}")
    private ResponseEntity<User> suspendUser(@PathVariable int uId, @RequestBody User user){
        user.setuId(uId);
        return ResponseEntity.ok().body(this.userService.suspendUser(user));
    }

    @PutMapping("/users/update/{uId}")
    private ResponseEntity<User> updateUser(@PathVariable int uId, @RequestBody User user){
        user.setuId(uId);
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }
    @PutMapping("/users/addmoney/{uId}")
    private ResponseEntity<User> addMoney(@PathVariable int uId, @RequestBody User user){
        user.setuId(uId);
        return ResponseEntity.ok().body(this.userService.addMoney(user));
    }


}