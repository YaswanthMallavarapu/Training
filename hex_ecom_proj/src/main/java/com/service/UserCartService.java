package com.service;

import com.exception.IllegalNameException;
import com.exception.IllegalPriceException;
import com.exception.IllegalStatusException;
import com.model.CartItem;
import com.model.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class UserCartService {
    public BigDecimal computeTotalCost(List<CartItem> items, User user) {

        BigDecimal total = items.stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Additional 10% discount if user is PREMIUM
        if (user.isStatus().equalsIgnoreCase("PREMIUM") && total.compareTo(BigDecimal.valueOf(500))>0) {
            total = total.multiply(BigDecimal.valueOf(0.90));
            return total;
        }


        // 5% discount if total > 1000
        if (total.compareTo(BigDecimal.valueOf(1000)) > 0) {
            total = total.multiply(BigDecimal.valueOf(0.95));

        }


        return total;
    }

    public void UserValidation(User user) throws IllegalStatusException {
        if(user==null){
            throw new NullPointerException("user cannot be null");
        }
        if (!user.isStatus().equalsIgnoreCase("PREMIUM")
                && !user.isStatus().equalsIgnoreCase("NORMAL")) {

            throw new IllegalStatusException(
                    "Status should be only PREMIUM or NORMAL"
            );
        }

    }

    public void ItemValidation(CartItem cartItem) throws NullPointerException,IllegalPriceException, IllegalNameException {
     if(cartItem==null){
         throw  new NullPointerException("item cannot be null");
     }
     if(cartItem.getName()==null || cartItem.getName()==""){
         throw new IllegalNameException("Name should't be null or empty");
     }
        if (cartItem.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalPriceException("Price cannot be negative");
        }

    }



}
