package org.ecom.exception;

public class ItemNotFoundException extends Exception{
    private String message;
    public ItemNotFoundException(String itemWithIdNotFound) {
        this.message=itemWithIdNotFound;
    }

    public String getMessage() {
        return message;
    }
}
