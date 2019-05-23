package com.chernyak.fapi.handler;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    public void handleException(Exception e, HttpServletResponse response) throws IOException {
        e.printStackTrace();
        response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    public ResponseEntity<String> handleHttpStatusCodeException(HttpStatusCodeException e) {
        String msg = e.getResponseBodyAsString();
        return new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public void handleResponseStatusException(ResponseStatusException e, HttpServletResponse response)
            throws IOException {
        response.sendError(e.getStatus().value(), e.getReason());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public void handleResponseStatusException(ExpiredJwtException e, HttpServletResponse response)
            throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public void handleBadCredentialsException(BadCredentialsException e, HttpServletResponse response)
            throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password.");
    }
}