package org.example.usersApp.dto;

public record UpdateUserDto(
        String firstName,
        String lastName,
        Integer age
) {}
