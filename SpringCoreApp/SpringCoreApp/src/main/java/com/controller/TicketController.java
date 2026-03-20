package com.controller;


import com.config.ProjConfig;
import com.enums.TicketPriority;
import com.enums.TicketStatus;
import com.exception.TicketIdNotFoundException;
import com.model.Ticket;
import com.service.TicketService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicketController {
    public static void main(String[] args) {
        var context =new AnnotationConfigApplicationContext(ProjConfig.class);
        TicketService ticketService = context.getBean(TicketService.class);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. insert ticket");
            System.out.println("2. delete ticket");
            System.out.println("3. print all tickets ");
            System.out.println("0. Exit");
            int choice = sc.nextInt();
            if (choice == 0) {
                break;
            }
            switch (choice) {
                case 1:
                    Ticket ticket = context.getBean(Ticket.class);
                    ticket.setSubject("Internet is not working");
                    ticket.setDetails("Jalid aao and thik karo");
                    ticket.setTicketStatus(TicketStatus.open);
                    ticket.setTicketPriority(TicketPriority.high);
                    ticket.setCreatedAt(Instant.now());

                    ticketService.insert(ticket);
                    System.out.println("insert op done .......!");
                    break;
                case 2:
                    System.out.println("Enter id of ticket to delete");
                    int id = sc.nextInt();
                    try {
                        ticketService.deleteTicketByID(id);
                    }catch (TicketIdNotFoundException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    List<Ticket> tickets = ticketService.fetchAllTickets();
                    tickets.forEach(System.out::println);
                    break;
                default:
                    System.out.println("Invalid choice. Try again");
                    break;

            }
        }


    }

}
