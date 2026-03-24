package org.hibernate.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.hibernate.dto.FlightBookingDto;
import org.hibernate.dto.FlightDto;
import org.hibernate.exception.ResourceNotFoundException;
import org.hibernate.model.Booking;
import org.hibernate.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class BookingService {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addBooking(Booking booking, int flightId) {
        Flight flight=(Flight)getFlightById(flightId);
        if(flight==null){
            throw new ResourceNotFoundException("flight with given id not found");
        }
        booking.setFlight(flight);
        entityManager.persist(booking);
    }



    public FlightBookingDto getFlightAndBookingsByDate(int flightId, LocalDate searchDate) {
        Flight flight=(Flight)getFlightById(flightId);
        Instant start=searchDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end=searchDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
        String jpql="select b from Booking b where b.flight.id=:flightId and" +
                " b.dateOfBooking >= :start and b.dateOfBooking < :end";
        Query query=entityManager.createQuery(jpql,Booking.class);
        query.setParameter("flightId",flightId);
        query.setParameter("start",start);
        query.setParameter("end",end);
        List<Booking> list=query.getResultList();

        return new FlightBookingDto(flight.getFlightNumber(),
                flight.getSource(),
                flight.getDestination(),
                flight.getAirline().getName(),
                list);


    }
    private Object getFlightById(int flightId) {
        String jpql="select f from Flight f where id=: flightId";
        Query query=entityManager.createQuery(jpql,Flight.class);
        query.setParameter("flightId",flightId);
        return query.getResultList().getFirst();
    }

    @Transactional
    public void updateBooking(Booking booking,int id) {
      //get criteria builder
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();

        // create criteria
        CriteriaUpdate<Booking> query=cb.createCriteriaUpdate(Booking.class);

        //create from
        Root<Booking> root=query.from(Booking.class);

        //updation
        if ( booking.getPassengerName()!= null) {
            query.set(root.get("passengerName"), booking.getPassengerName());
        }

        if (booking.getSeatNumber() != null) {
            query.set(root.get("seatNumber"), booking.getSeatNumber());
        }

        //create where
       query.where(cb.equal(root.get("id"),id));
        //return
        entityManager.createQuery(query).executeUpdate();

    }

    public BigDecimal getTotalFare(int flightId, LocalDate searchDate) {
        Flight flight=(Flight) getFlightById(flightId);
        Instant start=searchDate.atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant end=searchDate.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant();
        String jpql="select sum(b.price) from Booking b where b.flight.id=:id " +
                "and b.dateOfBooking >= :start and b.dateOfBooking < :end";
        Query query=entityManager.createQuery(jpql,BigDecimal.class);
        query.setParameter("id",flightId);
        query.setParameter("start",start);
        query.setParameter("end",end);
        return (BigDecimal) query.getSingleResult();
    }
}
