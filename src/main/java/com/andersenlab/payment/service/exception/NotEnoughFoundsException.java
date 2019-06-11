package com.andersenlab.payment.service.exception;

public class NotEnoughFoundsException extends RuntimeException {

    public NotEnoughFoundsException(String message) {
        super(message);
    }

}
