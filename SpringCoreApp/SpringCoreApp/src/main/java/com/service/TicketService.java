package com.service;

import com.exception.TicketIdNotFoundException;
import com.model.Ticket;
import com.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    public void insert(Ticket ticket) {
        ticketRepository.insert(ticket);
        return;
    }

    public void deleteTicketByID(int id) throws TicketIdNotFoundException {
        boolean check = ticketRepository.checkid(id);

        if(!check){
            throw new TicketIdNotFoundException("Id is not found");
        }else{
            ticketRepository.deleteTicketByID(id);
        }

    }

    public List<Ticket> fetchAllTickets() {
        return  ticketRepository.fetchAllTickets();
    }
}
