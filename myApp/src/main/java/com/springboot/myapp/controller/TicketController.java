package com.springboot.myapp.controller;

import com.springboot.myapp.dto.*;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.service.CustomerService;
import com.springboot.myapp.service.TicketService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ticket")
@CrossOrigin(origins = "http://localhost:5173/")
public class TicketController {
    private final TicketService ticketService;
    private final CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> addTicket(@Valid @RequestBody TicketReqDto ticketReqDto, Principal principal){


        Ticket ticket=ticketService.addTicket(ticketReqDto,principal.getName());
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



    @GetMapping("/get-all/{customerId}")
    public ResponseEntity<?> getAllTicketsByCustomerId(@PathVariable long customerId,
                                                                                           @RequestParam(value = "page",required = false,defaultValue = "0")int page,
                                                                                           @RequestParam(value = "size",required = false,defaultValue = "5") int size){
        List<TicketWithCustomerExecutiveResDto>list=ticketService.getAllTicketsByCustomer(customerId,page,size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
        
    }

    @GetMapping("/get-all/v2")
    public ResponseEntity<?> getAllTicketsByCustomer(
                                                     @RequestParam(value = "page",required = false,defaultValue = "0")int page,
                                                     @RequestParam(value = "size",required = false,defaultValue = "5") int size,
                                                     Principal principal){
        List<TicketWithCustomerExecutiveResDto>list=ticketService.getAllTicketsByCustomerV2(principal.getName(),page,size);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);

    }

    @PutMapping("/add-executive/{ticketId}/{executiveID}")
    public void addExecutiveToTicket(@PathVariable long ticketId,
                                     @PathVariable long executiveID){

        ticketService.addExecutiveToTicket(ticketId,executiveID);

    }

    @GetMapping("/stats")
    public ResponseEntity<List<StatDto>> getStats(Principal principal){
        List<StatDto>list=ticketService.getStats(principal.getName());
        return ResponseEntity
                .ok()
                .body(list);
    }

    @GetMapping("/stats/v2")
    public ResponseEntity<List<StatDtoV2>> getStatsV2(Principal principal){
        List<StatDtoV2>list=ticketService.getStatsV2(principal.getName());
        return ResponseEntity
                .ok()
                .body(list);
    }


    @PutMapping("/update/status/{ticketId}")
    public void updateStatus(@RequestParam TicketStatus ticketStatus,
                             @PathVariable long ticketId,
                             Principal principal){
        ticketService.updateStatus(ticketStatus, ticketId, principal.getName());
    }
}
