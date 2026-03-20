package org.ecom.controller;

import org.ecom.config.ProjConfig;
import org.ecom.dto.Item;
import org.ecom.enums.UserMemberShip;
import org.ecom.model.CartItem;
import org.ecom.model.User;
import org.ecom.service.CartService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

@Controller
public class CartItemController {
    public static void main(String[] args) {
        var context=new AnnotationConfigApplicationContext(ProjConfig.class);
        CartService cartService=context.getBean(CartService.class);
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("1. add cart item");
            System.out.println("2. all cart items");
            System.out.println("3. fetch items using username");
            System.out.println("4. delete item by id");
            System.out.println("0. exit");
            int choice =sc.nextInt();
            if(choice==0)break;
            switch (choice){
                case 1:
                    sc.nextLine();
                    System.out.println("Enter name of product: ");
                    String pname=sc.nextLine();
                    System.out.println("Enter price of product: ");
                    BigDecimal price=sc.nextBigDecimal();
                    System.out.println("Enter Quantity of product: ");
                    int qty=sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter name of user: ");
                    String uname=sc.nextLine();
                    System.out.println("Enter Membership of user: ");
                    String membership=sc.nextLine().toUpperCase();
                    User user=new User(UserMemberShip.valueOf(membership),uname);
                    CartItem cartItem=new CartItem(pname,price,qty,user);
                    cartService.addCartItem(cartItem);
                    break;
                case 2:
                    List<Item> items=cartService.getAllCartItems();
                    items.forEach(item -> {
                        System.out.println("Product ID      : " + item.pid());
                        System.out.println("Product Name    : " + item.pname());
                        System.out.println("Price           : " + item.price());
                        System.out.println("Quantity        : " + item.qty());
                        System.out.println("User ID         : " + item.uid());
                        System.out.println("User Name       : " + item.uname());
                        System.out.println("Membership      : " + item.memberShip());
                        System.out.println("------------------------------------");
                    });
                    break;
                case 3:
                    System.out.println("Enter username to get items: ");
                    sc.nextLine();
                    String username=sc.nextLine();

                    List<Item>items1=cartService.getItemByUserName(username);
                    if(items1.isEmpty()){
                        System.out.println("No items found with username "+username);
                    }
                    items1.forEach(item -> {
                        System.out.println("Product ID      : " + item.pid());
                        System.out.println("Product Name    : " + item.pname());
                        System.out.println("Price           : " + item.price());
                        System.out.println("Quantity        : " + item.qty());
                        System.out.println("User ID         : " + item.uid());
                        System.out.println("User Name       : " + item.uname());
                        System.out.println("Membership      : " + item.memberShip());
                        System.out.println("------------------------------------");
                    });
                    break;
                case 4:
                    System.out.println("Enter id of product to delete");
                    int id=sc.nextInt();
                    try{
                        cartService.deleteItembyId(id);
                        System.out.println("deleted item succesfully");
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }

                default:
                    System.out.println("Invalid Case");
            }
        }
    }
}
