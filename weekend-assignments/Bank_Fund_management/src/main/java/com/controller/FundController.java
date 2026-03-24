package com.controller;

import com.config.ProjConfig;
import com.model.Fund;
import com.model.Manager;
import com.service.FundService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.InstantSource;
import java.util.List;
import java.util.Scanner;

public class FundController {
    public static void main(String[] args) {
        var context=new AnnotationConfigApplicationContext(ProjConfig.class);
        FundService fundService=context.getBean(FundService.class);
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("1. Add Manager");
            System.out.println("2. Add Fund");
            System.out.println("3. Show Funds By Manager Name");
            System.out.println("0. Exit...");
            int choice=sc.nextInt();
            if(choice==0){
                break;
            }
            switch(choice){
                case 1:
                    Manager manager=new Manager();
                    System.out.println("Enter manager name");
                    manager.setName(sc.next());
                    System.out.println("Enter manager email");
                    manager.setEmail(sc.next());
                    fundService.addManager(manager);
                    System.out.println("Manager Added successfully!!");
                    break;
                case 2:
                    System.out.println("Enter Manager id");
                    Long managerId=sc.nextLong();
                    try {
                        manager = fundService.check(managerId);
                        Fund fund = new Fund();
                        System.out.println("Enter Fund name");
                        fund.setName(sc.next());
                        System.out.println("Enter fund amount");
                        fund.setAumAmount(new BigDecimal(sc.nextDouble()));
                        System.out.println("Enter Expense Ratio");
                        fund.setExpenseRatio(new BigDecimal(sc.nextDouble()));
                        fund.setCreatedAt(Instant.now());

                        fundService.addFund(fund,manager);
                        System.out.println("Fund added successfully!!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                case 3:
                    System.out.println("Enter manager name to get funds");
                    String name=sc.next().trim();
                    List<?> list=fundService.getFundsByManagerName(name);
                    if(list.isEmpty()){
                        System.out.println("Funds with the given manager name is Empty.");
                    }else{
                        list.forEach(System.out::println);
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        context.close();
    }
}
