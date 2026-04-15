package com.springboot.myapp.dto;

public record CustomerResponseDto(
        long id,
        String name,
        String email,
        String city
) {
}