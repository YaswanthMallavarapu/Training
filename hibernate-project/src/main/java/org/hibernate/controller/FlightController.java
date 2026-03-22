package org.hibernate.controller;

import org.hibernate.config.ProjConfig;
import org.hibernate.exception.ResourceNotFoundException;
import org.hibernate.model.Airline;
import org.hibernate.model.Flight;
import org.hibernate.service.AirlineService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;

import java.time.format.DateTimeParseException;
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
                case 3:
                    System.out.println("Enter airline id: ");
                    int airlineId=sc.nextInt();
                    try {
                        airline=airlineService.getAirLineById(airlineId);
                        Flight flight=new Flight();
                        System.out.println("Enter flight number: ");
                        flight.setFlightNumber(sc.next());

                        System.out.println("Enter flight source: ");
                        flight.setSource(sc.next());
                        System.out.println("Enter flight destination: ");
                        flight.setDestination(sc.next());
                        System.out.println("Enter flight departure time (hh:mm:ss) : ");
                        String departureTime=sc.next();
                        airlineService.insertFlight(flight,departureTime,airline);
                        System.out.println("flight added  successfully");


                    }catch(ResourceNotFoundException e){
                        System.out.println(e.getMessage());
                    } catch (DateTimeParseException e) {
                        System.out.println(e.getMessage());
                    }




                    break;
                case 4:
                    List<?>flights=airlineService.getAllFlights();
                    flights.forEach(System.out::println);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        sc.close();
        context.close();
    }
}
