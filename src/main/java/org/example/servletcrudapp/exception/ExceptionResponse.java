package org.example.servletcrudapp.exception;

import java.time.LocalDateTime;

public record ExceptionResponse (
        String message,
        int statusCode,
        LocalDateTime timestamp
) {}
