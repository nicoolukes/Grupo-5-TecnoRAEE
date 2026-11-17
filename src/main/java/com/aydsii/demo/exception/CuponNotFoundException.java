package com.aydsii.demo.exception;


public class CuponNotFoundException extends RuntimeException {

    public CuponNotFoundException(String message) {
        super(message);
    }
}
