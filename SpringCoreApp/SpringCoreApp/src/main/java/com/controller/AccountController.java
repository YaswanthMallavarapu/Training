package com.controller;

import com.config.ProjConfig;
import com.service.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

@Controller
public class AccountController {
    public static void main(String[] args) {
        var context= new AnnotationConfigApplicationContext(ProjConfig.class);
        AccountService accountService=context.getBean(AccountService.class);
        try{
            accountService.TransferMoneyFromABCToXYZ("ABC","XYZ",500);
            System.out.println("Transaction success!!");
        } catch (RuntimeException e) {
            System.out.println("money cedited back to your account");
        }

    }


}
