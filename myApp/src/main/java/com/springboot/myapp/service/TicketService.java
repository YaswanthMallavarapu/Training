package com.springboot.myapp.service;

import com.springboot.myapp.dto.TicketReqDto;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.mapper.TicketMapper;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.repository.TicketRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public Ticket addTicket(@Valid TicketReqDto ticketReqDto) {
        //convert to ticket
        Ticket ticket= TicketMapper.mapToTicket(ticketReqDto);
        //add additional
        ticket.setTicketStatus(TicketStatus.OPEN);
        //save in repository
        return ticketRepository.save(ticket);

    }
}
