package org.practice.ExceptionHandlingV2;

@FunctionalInterface
public interface RetryableOperation<T> {
    T execute() throws Exception;
}