package org.example.servletcrudapp.dto;

public record CreateUserDto(
        String firstName,
        String lastName,
        Integer age
) {
}
