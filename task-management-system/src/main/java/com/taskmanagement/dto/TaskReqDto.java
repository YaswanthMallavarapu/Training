package com.taskmanagement.dto;

import com.taskmanagement.enums.Priority;

import java.time.LocalDate;

public record TaskReqDto(

        String title,
        String description,
        LocalDate DueDate,
        String priority

        ) {
}
