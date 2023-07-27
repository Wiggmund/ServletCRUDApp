package org.example.servletcrudapp.dto;

public record UpdateUserDto(
        Long id,
        String firstName,
        String lastName,
        Integer age
) {}
