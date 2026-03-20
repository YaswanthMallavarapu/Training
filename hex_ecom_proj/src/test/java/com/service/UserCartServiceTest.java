package com.service;

import com.exception.IllegalNameException;
import com.exception.IllegalPriceException;
import com.exception.IllegalStatusException;
import com.model.CartItem;
import com.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserCartServiceTest {

    UserCartService userCartService=null;
    @BeforeEach
    public void init(){
        userCartService=new UserCartService();
    }
    @AfterEach
    public void destroy(){
        userCartService=null;
    }
    @Test
    public void computeTotalCostTest(){
        User user=new User(1,"Premium","yaswanth");
        CartItem cartItem=new CartItem(1,"pen",new BigDecimal(10),2);
        CartItem cartItem1=new CartItem(2,"mobile",new BigDecimal(1000),1);
        List<CartItem>items=new ArrayList<>();
        items.add(cartItem1);
        items.add(cartItem);

        // deafult validation
        Assertions.assertNotEquals(0,userCartService.computeTotalCost(items,user));
        //validation for premium user with morethan 500
        Assertions.assertEquals(new BigDecimal("918.0"),userCartService.computeTotalCost(items,user));

        // validation for normal user with price morethan 1000

        User user2=new User(3,"Normal","evado");
        Assertions.assertEquals(new BigDecimal("969.00"),userCartService.computeTotalCost(items,user2));
        //for normal not morethan 1000
        List<CartItem>list2=new ArrayList<>();
        list2.add(cartItem);
        Assertions.assertEquals(new BigDecimal("20"),userCartService.computeTotalCost(list2,user2));

    }
    @Test
    public void UserValidation(){
      User user=null;
      // validating null user
        NullPointerException ne=Assertions.assertThrows(NullPointerException.class,()->userCartService.UserValidation(user));
        Assertions.assertEquals("user cannot be null",ne.getMessage());

        // validating user status either premium or normal
        User user1=new User(1,"PREMIUM","yaswanth");
        Assertions.assertDoesNotThrow(()->userCartService.UserValidation(user1));


        User user2=new User(2,"diamond","yash");
        IllegalStatusException ise1=Assertions.assertThrows(IllegalStatusException.class,()->userCartService.UserValidation(user2));
        Assertions.assertEquals("status should be only PREMIUM or NORMAL".toLowerCase(), ise1.getMessage().toLowerCase());
    }

    @Test
    public void ItemValidationTest(){
      CartItem cartItem=null;

      // validating null cartItem
      NullPointerException ne=Assertions.assertThrows(NullPointerException.class,()->userCartService.ItemValidation(cartItem));
      Assertions.assertEquals("item cannot be null".toLowerCase(),ne.getMessage());

      //validating cartitem name
        CartItem item2=new CartItem(1,"",new BigDecimal(10),2);
        IllegalNameException ine=Assertions.assertThrows(IllegalNameException.class,()->userCartService.ItemValidation(item2));
        Assertions.assertEquals("Name should't be null or empty",ine.getMessage());

      //validating cartitem price
        CartItem cartItem2=new CartItem(2,"choclate",new BigDecimal(-1),3);
        IllegalPriceException ipe=Assertions.assertThrows(IllegalPriceException.class,()->userCartService.ItemValidation(cartItem2));
        Assertions.assertEquals("Price cannot be negative",ipe.getMessage());


    }
}
