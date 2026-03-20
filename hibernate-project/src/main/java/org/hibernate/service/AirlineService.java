package org.hibernate.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.model.Airline;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
