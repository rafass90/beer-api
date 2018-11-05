package com.ciclic.beerapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ciclic.beerapi.domain.dto.ErrorDTO;
import com.ciclic.beerapi.repository.ResourceDuplicatedException;
import com.ciclic.beerapi.repository.ResourceNotFoundException;

@ControllerAdvice
@EnableWebMvc
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
 
    @ExceptionHandler(value = ResourceNotFoundException.class)
    @ResponseBody
    protected ResponseEntity<ErrorDTO> handleResourceNotFound(Exception ex) {
        ErrorDTO error = new ErrorDTO(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(value = ResourceDuplicatedException.class)
    @ResponseBody
    protected ResponseEntity<ErrorDTO> handleResourceDuplicated(Exception ex) {
        ErrorDTO error = new ErrorDTO(HttpStatus.CONFLICT, ex.getMessage());
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    protected ResponseEntity<ErrorDTO> handleResourceThrowable(Throwable t) {
        ErrorDTO error = new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error has occurred");
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}