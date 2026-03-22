package org.hibernate.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.exception.ResourceNotFoundException;
import org.hibernate.model.Airline;
import org.hibernate.model.Flight;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AirlineService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insert(Airline airline) {
       em.persist(airline);

    }

    public List<Airline> getAllAirlines() {
        String jpql="select a from Airline a";
        Query query=em.createQuery(jpql,Airline.class);
        return query.getResultList();
    }

    public Airline getAirLineById(int airlineId) {
        Airline airline=em.find(Airline.class,airlineId);
        if(airline==null){
            throw new ResourceNotFoundException("Airline not found with id ...");
        }
        return airline;
    }

    @Transactional
    public void insertFlight(Flight flight, String departureTime, Airline airline) {
        LocalTime time=LocalTime.parse(departureTime, DateTimeFormatter.ISO_LOCAL_TIME);
        flight.setDepartureTime(time);
        flight.setAirline(airline);
        em.persist(flight);
    }

    public List<?> getAllFlights() {
        String jpql="select f from Flight f";
        Query query=em.createQuery(jpql,Flight.class);
        return query.getResultList();
    }
}
