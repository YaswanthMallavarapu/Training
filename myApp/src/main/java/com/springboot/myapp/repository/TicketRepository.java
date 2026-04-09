package com.springboot.myapp.repository;


import com.springboot.myapp.enums.TicketPriority;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.model.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByTicketPriorityAndTicketStatus(TicketPriority ticketPriority, TicketStatus ticketStatus);

    @Query("""
            select t from Ticket t
            where (?1 is null or t.ticketPriority=?1)
            and (?2 is null or t.ticketStatus=?2)
            """)
    List<Ticket> getByPriorityAndStatus(TicketPriority ticketPriority, TicketStatus ticketStatus);

    @Query("""
           select t from Ticket t
                      where t.customer.id=?1
           """)
    Page<Ticket> getAllByCustomer(Long id, Pageable pageable);
}
