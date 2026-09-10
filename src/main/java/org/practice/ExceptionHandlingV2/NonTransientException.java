package org.practice.ExceptionHandlingV2;

public class NonTransientException extends RuntimeException {
    public NonTransientException(String msg) {
        super(msg);
    }
}