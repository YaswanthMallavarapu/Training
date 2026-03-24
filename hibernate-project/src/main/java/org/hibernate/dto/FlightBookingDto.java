package org.hibernate.dto;

import org.hibernate.model.Booking;

import java.util.List;

public record FlightBookingDto(
        String flightNumber,
        String flightSource,
        String flightDestination,
        String airlineName,
        List<Booking> bookings
){
}
