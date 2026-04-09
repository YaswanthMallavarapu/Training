package com.springboot.myapp.service;

import com.springboot.myapp.dto.TicketResDto;
import com.springboot.myapp.enums.TicketPriority;
import com.springboot.myapp.enums.TicketStatus;
import com.springboot.myapp.exceptions.ResourceNotFoundException;
import com.springboot.myapp.model.Ticket;
import com.springboot.myapp.repository.TicketRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;
    @Mock
    private TicketRepository ticketRepository;

    @Test
    public void getByIdTestWhenExists(){
        //check for null pointers
        Assertions.assertNotNull(ticketService);

        //creating mock ticket
        Ticket ticket=new Ticket();
        ticket.setId(1L);
        ticket.setSubject("test subject");
        ticket.setTicketPriority(TicketPriority.HIGH);
        ticket.setTicketStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(Instant.now());

        //creating res dto
        TicketResDto dto=new TicketResDto(
                ticket.getId(),
                ticket.getSubject(),
                ticket.getTicketPriority(),
                ticket.getTicketStatus(),
                ticket.getCreatedAt()
        );

        TicketResDto dto1=new TicketResDto(
                ticket.getId(),
                ticket.getSubject(),
                ticket.getTicketPriority(),
                TicketStatus.IN_PROCESS,
                ticket.getCreatedAt()
        );

        //process when from service repo method is called which doesn't exist
        //so it mocks repo and will send the response we specify to service
        //then our service do business logic and returns output
        //then we compare that output with defined result
        //in this way we can test our service without relaying on underlying repo and DB
        Mockito.when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        //verifying
        Assertions.assertEquals(dto,ticketService.getTicketById(1L));
        Assertions.assertNotEquals(dto1,ticketService.getTicketById(1L));

        //verifying Repo calls or DB calls
        Mockito.verify(ticketRepository,Mockito.times(2)).findById(1L);

    }

    @Test
    public void getByIdTestWhenNotExist(){

        //mocking when id not found
        Mockito.when(ticketRepository.findById(11L)).thenReturn(Optional.empty());


        //catching exception when id not found
        Exception e=Assertions.assertThrows(ResourceNotFoundException.class,()->ticketService.getTicketById(11L));

        //check for error message
        Assertions.assertEquals("Invalid id...",e.getMessage());

    }

    @Test
    public void getAllTickets(){
        /* Prepare the List. */
        Ticket ticket1 = new Ticket();
        ticket1.setId(12L);
        ticket1.setSubject("test subject");
        ticket1.setTicketPriority(TicketPriority.LOW);
        ticket1.setTicketStatus(TicketStatus.OPEN);
        ticket1.setCreatedAt(Instant.now());
        Ticket ticket2 = new Ticket();
        ticket2.setId(14L);
        ticket2.setSubject("test subject");
        ticket2.setTicketPriority(TicketPriority.HIGH);
        ticket2.setTicketStatus(TicketStatus.CLOSE);
        ticket2.setCreatedAt(Instant.now());
        List<Ticket> list = List.of(ticket1,ticket2);


        Page<Ticket> pageTicket=new PageImpl<>(list);
        int page=0;
        int size=2;
        Pageable pageable= PageRequest.of(page,size);
        Mockito.when(ticketRepository.findAll(pageable)).thenReturn(pageTicket);
        Assertions.assertEquals(2,ticketService.getAllTickets(0,2).data().size());

        pageTicket=new PageImpl<>(list.subList(0,1));
        page=0;
        size=1;
        pageable= PageRequest.of(page,size);
        Mockito.when(ticketRepository.findAll(pageable)).thenReturn(pageTicket);
        Assertions.assertEquals(1,ticketService.getAllTickets(0,1).data().size());

        pageTicket=new PageImpl<>(list.subList(0,2));
        page=0;
        size=3;
        pageable= PageRequest.of(page,size);
        Mockito.when(ticketRepository.findAll(pageable)).thenReturn(pageTicket);
        Assertions.assertEquals(2,ticketService.getAllTickets(0,3).data().size());
    }

}
