package com.exception;

public class IllegalPriceException extends Exception {
    public IllegalPriceException(String priceCannotBeNegative) {
        super(priceCannotBeNegative);
    }
}
