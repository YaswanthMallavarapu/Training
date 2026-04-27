package com.micro.account_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AccountController {

    @GetMapping("/hello")
    public String sayHello(){
        return "hello account service";
    }
}
