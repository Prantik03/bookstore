package com.bookstore.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class userException  extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public userException(String message){
        super(message);
    }
    public userException(String message, Throwable throwable){
        super(message, throwable);
    }
}