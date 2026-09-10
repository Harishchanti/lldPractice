package org.practice.ExceptionHandlingV2;


public class TransientException extends RuntimeException {
    public TransientException(String msg) {
        super(msg);
    }
}
