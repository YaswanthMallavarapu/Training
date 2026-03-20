package com.exception;

public class TicketIdNotFoundException extends Exception{
    private String message;
    public TicketIdNotFoundException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}
