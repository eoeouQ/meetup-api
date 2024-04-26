package org.izouir.meetup_api.exception;

public class InvalidDateFormatException extends IllegalArgumentException {
    public InvalidDateFormatException(final String message) {
        super(message);
    }
}
