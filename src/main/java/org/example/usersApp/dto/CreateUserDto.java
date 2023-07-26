package org.example.usersApp.dto;

public record CreateUserDto(
        String firstName,
        String lastName,
        Integer age
) {}
