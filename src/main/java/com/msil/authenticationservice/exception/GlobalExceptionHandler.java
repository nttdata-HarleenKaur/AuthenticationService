package com.msil.authenticationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.msil.authenticationservice.model.response.UserAuthenticationFailedResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<UserAuthenticationFailedResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Error decoding password", e);
        UserAuthenticationFailedResponse response=UserAuthenticationFailedResponse.builder()
        		.statusCode(HttpStatus.BAD_REQUEST.toString()).statusMessage(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

   

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserAuthenticationFailedResponse> handleGeneralException(Exception e) {
        log.error("An unexpected error occurred with DMS api", e);
        UserAuthenticationFailedResponse response=UserAuthenticationFailedResponse.builder()
        		.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString()).statusMessage(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<UserAuthenticationFailedResponse> handleInvalidCredentialsException(Exception e) {
        log.error("An unexpected error occurred with DMS api", e);
        UserAuthenticationFailedResponse response=UserAuthenticationFailedResponse.builder()
        		.statusCode(HttpStatus.BAD_REQUEST.toString()).statusMessage(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
