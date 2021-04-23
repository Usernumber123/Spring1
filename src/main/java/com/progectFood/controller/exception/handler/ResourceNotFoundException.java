package com.progectFood.controller.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ResourceNotFoundException {
  //  @ExceptionHandler(PersonNotFoundException.class)
 //   @ResponseStatus(HttpStatus.NOT_FOUND)
  //  public String handleException(PersonNotFoundException exception) {
    //    return exception.getMessage();
  //  }
}
