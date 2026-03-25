package com.springboot.myapp.service;

import com.springboot.myapp.dto.FilterReqDto;
import com.springboot.myapp.dto.TicketPageResDto;
import com.springboot.myapp.dto.TicketReqDto;
import com.springboot.myapp.dto.TicketResDto;
import com.springboot.myapp.enums.TicketPriority;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.exceptions.ResourceNotFoundException;
import com.springboot.myapp.mapper.TicketMapper;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.repository.TicketRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

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

    public TicketPageResDto getAllTickets(int page, int size) {
        //create a pageable interface
        Pageable pageable= PageRequest.of(page,size);

        //getting data from database using pageable object
        Page<Ticket> ticketPage=ticketRepository.findAll(pageable);
        Long totalRecords=ticketPage.getTotalElements();
        int totalPages=ticketPage.getTotalPages();
        List<TicketResDto>listDto=ticketPage
                .toList()
                .stream()
                .map(TicketMapper::mapToDto)
                .toList();
        return new TicketPageResDto(
                listDto,
                totalRecords,
                totalPages
        );
    }

    public TicketResDto getTicketById(long id) {
        Ticket ticket=ticketRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Invalid id..."));
        return new TicketResDto(
                ticket.getId(),
                ticket.getSubject(),
                ticket.getTicketPriority(),
                ticket.getTicketStatus(),
                ticket.getCreatedAt()
        );
    }

    public List<Ticket> filterTickets(FilterReqDto filterReqDto) {
        if(filterReqDto.priority() ==null && filterReqDto.status()==null) {
            return List.of();
        }
        TicketPriority priority=(filterReqDto.priority()!=null && !filterReqDto.priority().isEmpty())
                ?TicketPriority.valueOf(filterReqDto.priority()):null;
        com.springboot.myapp.enums.TicketStatus status =(filterReqDto.status()!=null && !filterReqDto.status().isEmpty())
                ?TicketStatus.valueOf(filterReqDto.status()):null;
        return ticketRepository.getByPriorityAndStatus(priority,status);
    }
}
