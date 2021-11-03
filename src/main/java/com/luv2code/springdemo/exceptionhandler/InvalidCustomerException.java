package com.luv2code.springdemo.exceptionhandler;

public class InvalidCustomerException extends RuntimeException{
    public InvalidCustomerException() {
        super();
    }

    public InvalidCustomerException(String message) {
        super(message);
    }

    public InvalidCustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCustomerException(Throwable cause) {
        super(cause);
    }

    protected InvalidCustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}