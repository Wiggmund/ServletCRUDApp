package org.examplle.servletcrudapp.dto;

public record CreateUserDto(
        String firstName,
        String lastName,
        Integer age
) {}
