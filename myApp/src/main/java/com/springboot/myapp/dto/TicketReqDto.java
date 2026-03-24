package com.springboot.myapp.dto;

import com.springboot.myapp.enums.TicketPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketReqDto(
        @NotNull(message = "subject cannot be null")
        @NotBlank(message = "subject cannot be blank")
        @Size(min=3,max=255,message="subject must be of size 3-255")
        String subject,
        @NotBlank(message = "details cannot be blank")
        @NotNull(message = "details cannot be null")
        @Size(min=3,max=1000,message = "details must be of length 3-1000")
        String details,
        TicketPriority ticketPriority
) {
}
