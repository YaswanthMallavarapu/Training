package com.springboot.myapp.dto;

import com.springboot.myapp.enums.JobTitle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExecutiveDto(
        @NotNull(message = "name can not be null")
        @NotBlank(message="name can not be blank")
        @Size(min=3,max=255,message="name should be of length 3-255")
        String name,
        JobTitle jobTitle
) {
}
