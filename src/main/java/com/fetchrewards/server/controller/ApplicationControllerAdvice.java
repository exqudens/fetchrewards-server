package com.fetchrewards.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(RuntimeException.class)
  public void handle(RuntimeException e) {
    log.error(e.getMessage(), e);
    throw e;
  }

}
