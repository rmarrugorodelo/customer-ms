package com.rmarrugo.exception;

import lombok.Getter;

@Getter
public class PreconditionFailedException extends Exception{

    private final String message;

    public PreconditionFailedException(String message) {
        super(message);
        this.message = message;
    }
    
}
