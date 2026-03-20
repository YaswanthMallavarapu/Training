package org.hibernate.controller;

import org.hibernate.config.ProjConfig;
import org.hibernate.model.Airline;
import org.hibernate.service.AirlineService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;


public class FlightController {
    public static void main(String[] args) {
        var context=new AnnotationConfigApplicationContext(ProjConfig.class);
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
                =context.getBean(LocalContainerEntityManagerFactoryBean.class);
        AirlineService airlineService=context.getBean(AirlineService.class);
//        System.out.println(entityManagerFactoryBean);
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("1. Insert Airline");
            System.out.println("2. Show all Airlines");
            System.out.println("3. Insert Flight");
            System.out.println("4. Show all flights");
            System.out.println("5. Update flight");
            System.out.println("6. Delete flight by ID");
            System.out.println("0. Exit");
            int choice=sc.nextInt();
            if(choice==0)
                break;
            switch(choice){
                case 1:
                    Airline airline=new Airline();
                    System.out.println("Enter flight name");
                    sc.nextLine();
                    String name=sc.nextLine();
                    System.out.println("Enter flight country");
                    String country=sc.nextLine();
                    airline.setName(name);
                    airline.setCountry(country);
                    airlineService.insert(airline);
                    System.out.println("Airline added...");
                    break;
                case 2:
                    List<Airline> list=airlineService.getAllAirlines();
                    list.forEach(System.out::println);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        sc.close();
        context.close();
    }
}
