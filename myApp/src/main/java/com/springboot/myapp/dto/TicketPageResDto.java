package com.springboot.myapp.dto;

import com.springboot.myapp.model.Ticket;

import java.util.List;

public record TicketPageResDto(
        List<TicketResDto> data,
        Long totalRecords,
        int pages
) {
}
