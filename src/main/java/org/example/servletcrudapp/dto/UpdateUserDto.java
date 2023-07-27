package org.example.servletcrudapp.dto;

public record UpdateUserDto(
        String firstName,
        String lastName,
        Integer age
) {}
