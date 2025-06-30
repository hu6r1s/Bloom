package com.hu6r1s.bloom.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LikeDuplicationException extends RuntimeException {
  public LikeDuplicationException(String message) {
    super(message);
  }
}
