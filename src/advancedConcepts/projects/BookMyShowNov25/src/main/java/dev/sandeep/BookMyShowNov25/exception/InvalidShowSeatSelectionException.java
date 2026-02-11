package dev.sandeep.BookMyShowNov25.exception;

public class InvalidShowSeatSelectionException extends RuntimeException{

    public InvalidShowSeatSelectionException(String message) {
        super(message);
    }

    public InvalidShowSeatSelectionException() {
    }
}
