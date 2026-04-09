package com.springboot.myapp.service;

import com.springboot.myapp.dto.*;
import com.springboot.myapp.enums.TicketPriority;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.exceptions.ResourceNotFoundException;
import com.springboot.myapp.mapper.TicketMapper;
import com.springboot.myapp.model.Customer;
import com.springboot.myapp.model.Executive;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.repository.TicketRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CustomerService customerService;
    private final ExecutiveService executiveService;

    public Ticket addTicket(@Valid TicketReqDto ticketReqDto, String username) {
        //check for customer

        Customer customer=customerService.getByUsername(username);
        System.out.println(customer);
        //convert to ticket
        Ticket ticket= TicketMapper.mapToTicket(ticketReqDto);
        //add additional
        ticket.setTicketStatus(TicketStatus.OPEN);
        ticket.setCustomer(customer);
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


    public void addExecutiveToTicket(long ticketId, long executiveID) {
        //get ticket
        Ticket ticket=getById(ticketId);
        //get executive

        Executive executive=executiveService.getExecutiveById(executiveID);

        //attach executive to ticket
        ticket.setExecutive(executive);
        //save ticket
        ticketRepository.save(ticket);
    }

    public Ticket getById(long id){
        return ticketRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Invalid Ticket id."));
    }

    public List<TicketWithCustomerExecutiveResDto> getAllTicketsByCustomer(long customerId, int page, int size) {
        //check for customer
        customerService.getById(customerId);
        //create pagination
        Pageable pageable=PageRequest.of(page,size);
        Page<Ticket>pageList=ticketRepository.findAll(pageable);
        return pageList
                .toList()
                .stream()
                .map(TicketMapper::mapToResDto)
                .toList();


    }

    public List<TicketWithCustomerExecutiveResDto> getAllTicketsByCustomerV2(String name, int page, int size) {
        //check for customer
        Customer customer=customerService.getByCustomer(name);
        //create pagination
        Pageable pageable=PageRequest.of(page,size);
        Page<Ticket>pageList=ticketRepository.getAllByCustomer(customer.getId(),pageable);
        return pageList
                .toList()
                .stream()
                .map(TicketMapper::mapToResDto)
                .toList();
    }
}
