package ru.darek;

public class AppArraySizeException extends RuntimeException{
    public AppArraySizeException() {
    }

    public AppArraySizeException(String message) {
        super(message);
    }

    public AppArraySizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
