package org.pika.my_housing.exceptions;

public class ApiException extends RuntimeException {
    public ApiException(String s) {
        super(s);
    }

    public ApiException() {
        super();
    }
}
