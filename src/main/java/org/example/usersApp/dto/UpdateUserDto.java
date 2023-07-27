package org.example.usersApp.dto;

import java.util.Map;

public record UpdateUserDto(
        Long id,
        String firstName,
        String lastName,
        Integer age
) {}
