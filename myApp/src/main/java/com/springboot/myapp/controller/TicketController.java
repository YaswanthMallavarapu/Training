package com.springboot.myapp.controller;

import com.springboot.myapp.dto.*;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.service.CustomerService;
import com.springboot.myapp.service.TicketService;
import jakarta.servlet.ServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;
    private final CustomerService customerService;

    @PostMapping("/add/{customerId}")
    public ResponseEntity<?> addTicket(@Valid @RequestBody TicketReqDto ticketReqDto,@PathVariable long customerId){

        Ticket ticket=ticketService.addTicket(ticketReqDto,customerId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ticket);
    }

    @GetMapping("/get-all")
    public TicketPageResDto getAllTickets(@RequestParam(value = "page",required = false,defaultValue = "0")int page,
                                          @RequestParam(value = "size",required = false,defaultValue = "5")int size){
        return ticketService.getAllTickets(page,size);
    }

    @GetMapping("/get/{id}")
    public TicketResDto getTicketById(@PathVariable long id){
        return ticketService.getTicketById(id);
    }

    @GetMapping("/get/filter")
    public List<Ticket> filterTickets(@RequestBody FilterReqDto filterReqDto){
        return ticketService.filterTickets(filterReqDto);

    }

    @PutMapping("/add-executive/{ticketId}/{executiveID}")
    public void addExecutiveToTicket(@PathVariable long ticketId,
                                     @PathVariable long executiveID, ServletRequest servletRequest){

        ticketService.addExecutiveToTicket(ticketId,executiveID);

    }

    @GetMapping("/get-all/{customerId}")
    public ResponseEntity<?> getAllTicketsByCustomer(@PathVariable long customerId,
                                                                                           @RequestParam(value = "page",required = false,defaultValue = "0")int page,
                                                                                           @RequestParam(value = "size",required = false,defaultValue = "5") int size){
        List<TicketWithCustomerExecutiveResDto>list=ticketService.getAllTicketsByCustomer(customerId,page,size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
        
    }
}
