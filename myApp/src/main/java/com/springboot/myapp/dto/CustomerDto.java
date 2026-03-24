package com.springboot.myapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerDto(
        @NotBlank(message = "name should not be blank")
        @NotNull(message = "name should not be null")
        @Size(min=3,max=255,message = "name must be of length 3-255")
        String name,
        @NotBlank(message = "email should not be blank")
        @NotNull(message = "email should not be null")
        @Size(min=3,max=255,message = "email must be of length 3-255")
        String email,
        @NotBlank(message = "city should not be blank")
        @NotNull(message = "city should not be null")
        @Size(min=3,max=255,message = "name must be of length 3-255")
        String city
) {
}
