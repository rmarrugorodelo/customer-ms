package com.rmarrugo.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends Exception{

    private final String message;

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
