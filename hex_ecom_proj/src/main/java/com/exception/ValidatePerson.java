package com.exception;

public class ValidatePerson extends RuntimeException{
    private String message;
    public ValidatePerson(String message){
        super(message);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
