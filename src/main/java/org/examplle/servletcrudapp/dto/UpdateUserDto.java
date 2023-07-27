package org.examplle.servletcrudapp.dto;

public record UpdateUserDto(
        String firstName,
        String lastName,
        Integer age
) {}
