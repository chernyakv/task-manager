package com.chernyak.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.RollbackException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e, HttpServletResponse response) throws IOException {
        e.printStackTrace();
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public void handleConstraintViolationException(ConstraintViolationException e, HttpServletResponse response)
            throws IOException {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String errors = violations.stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("."));
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), errors);
    }

    @ExceptionHandler({TransactionSystemException.class})
    public void handleRollbackException(TransactionSystemException e, HttpServletResponse response)
            throws IOException {
        Throwable t = e.getCause();
        while ((t != null) && !(t instanceof ConstraintViolationException)) {
            t = t.getCause();
            ConstraintViolationException exc = (ConstraintViolationException)(t);
            handleConstraintViolationException(exc, response);
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public void handleResponseStatusException(ResponseStatusException e, HttpServletResponse response) throws IOException {
        response.sendError(e.getStatus().value(), e.getReason());
    }
}
