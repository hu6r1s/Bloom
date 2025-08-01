package com.hu6r1s.bloom.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ProfileNotfoundException extends RuntimeException {

  public ProfileNotfoundException(String message) {
    super(message);
  }
}
