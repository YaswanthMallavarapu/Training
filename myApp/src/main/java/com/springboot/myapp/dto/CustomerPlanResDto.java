package com.springboot.myapp.dto;

import java.time.LocalDate;

public record CustomerPlanResDto(
        long customerId,
        String name,
        String email,
        String city,
        LocalDate startDate,
        LocalDate endDate,
        String planName
) {
}
