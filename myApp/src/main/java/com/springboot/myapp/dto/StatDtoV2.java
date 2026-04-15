package com.springboot.myapp.dto;

import com.springboot.myapp.enums.TicketStatus;

public record StatDtoV2(
        TicketStatus status,
        long count
) {
}
