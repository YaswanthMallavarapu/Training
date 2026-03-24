package org.hibernate.controller;

import org.hibernate.config.ProjConfig;
import org.hibernate.dto.FlightBookingDto;
import org.hibernate.model.Booking;
import org.hibernate.service.BookingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class BookingController {
    public static void main(String[] args) {
        var context=new AnnotationConfigApplicationContext(ProjConfig.class);
        BookingService bookingService=context.getBean(BookingService.class);
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("1. Add Booking");
            System.out.println("2. select Booking along with flight details for certain flight on given date");
            System.out.println("3. update booking dynamically");
            System.out.println("4. display total fare for a flight based on date");
            System.out.println("0. Exit");
            int choice=sc.nextInt();
            if(choice==0)
                break;
            switch(choice){
                case 1:
                    Booking booking=new Booking();
                    System.out.println("Enter Passenger Name:");
                    booking.setPassengerName(sc.next());
                    System.out.println("Enter seat number:");
                    booking.setSeatNumber(sc.next());
                    System.out.println("Enter fare pf seat:");
                    booking.setPrice(sc.nextBigDecimal());
                    System.out.println("Enter age of customer:");
                    booking.setAge(sc.nextInt());
                    System.out.println("Enter id of flight:");
                    int flightId=sc.nextInt();
                    bookingService.addBooking(booking,flightId);
                    System.out.println("Added booking successfully!!");

                    break;
                case 2:
                    System.out.println("Enter Flight Id :");
                    flightId=sc.nextInt();
                    System.out.println("Enter Date (yyyy-mm-dd) of flight to search");
                    String date=sc.next();
                    LocalDate searchDate= LocalDate.parse(date);
                    FlightBookingDto flightBookingDto=bookingService.getFlightAndBookingsByDate(flightId,searchDate);
                    System.out.println(flightBookingDto.flightNumber()+"  "+flightBookingDto.flightSource());
                    System.out.println(flightBookingDto.airlineName()+"   "+flightBookingDto.flightDestination());
                    flightBookingDto.bookings().forEach(System.out::println);
                    break;
                case 3:
                    int id=1;
                    Booking booking1=new Booking();
                    booking1.setPassengerName("yaswanth mallavarapu");
                    Booking booking2=new Booking();
                    booking2.setPassengerName("yaswanth m");
                    booking2.setSeatNumber("s67");
                    bookingService.updateBooking(booking2,id);
                    System.out.println("booking updation successful");
                    break;
                case 4:
                    System.out.println("Enter flight id:");
                    flightId=sc.nextInt();
                    System.out.println("ENter date(yyyy-mm-dd):");
                    LocalDate d=LocalDate.parse(sc.next());
                    BigDecimal total=bookingService.getTotalFare(flightId,d);
                    System.out.println("total fare collected "+total);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
