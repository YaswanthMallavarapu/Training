package org.hibernate.dto;

import org.hibernate.model.Employee;

import java.util.List;

public record FlightDto(
        String flightNumber,
        String flightSource,
        String flightDestination,
        String airlineName,
        List<Employee>list

) {
}
