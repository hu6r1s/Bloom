package com.hu6r1s.bloom.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SelfLikeException extends RuntimeException {

  public SelfLikeException(String message) {
    super(message);
  }
}
