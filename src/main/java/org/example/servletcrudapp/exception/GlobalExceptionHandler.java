package org.example.servletcrudapp.exception;

import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class GlobalExceptionHandler {
    public static ExceptionResponse handleException(RuntimeException initialException) {
        if (initialException instanceof UserNotFoundException exception) {
            return handleException(exception);
        }

        if (initialException instanceof UserAlreadyExistException exception) {
            return handleException(exception);
        }

        if (initialException instanceof DBInternalException exception) {
            return handleException(exception);
        }

        if (initialException instanceof IllegalArgumentException exception) {
            return handleException(exception);
        }

        return buildResponse(initialException.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    private static ExceptionResponse buildResponse(String message, int statusCode) {
        return new ExceptionResponse(
                message,
                statusCode,
                LocalDateTime.now()
        );
    }

    private static ExceptionResponse handleException(UserNotFoundException exception) {
        return buildResponse(exception.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
    }

    private static ExceptionResponse handleException(UserAlreadyExistException exception) {
        return buildResponse(exception.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
    }

    private static ExceptionResponse handleException(IllegalArgumentException exception) {
        return buildResponse(exception.getMessage(), HttpServletResponse.SC_BAD_REQUEST);
    }

    private static ExceptionResponse handleException(DBInternalException exception) {
        return buildResponse(exception.getMessage(), HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}
