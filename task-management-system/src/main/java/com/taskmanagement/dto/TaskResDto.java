package com.taskmanagement.dto;

import com.taskmanagement.enums.Priority;
import com.taskmanagement.enums.Status;

import java.time.LocalDate;

public record TaskResDto(
        long id,
        String title,
        String description,
        LocalDate dueDate,
        Priority priority,
        Status status
) {
}
