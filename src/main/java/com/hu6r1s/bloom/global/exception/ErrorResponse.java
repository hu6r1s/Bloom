package com.hu6r1s.bloom.global.exception;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ErrorResponse {

  private final LocalDateTime timestamp = LocalDateTime.now();
  private final int status;
  private final String error;
  private final String message;
  private final String path;

  public ErrorResponse(int status, String error, String message, String path) {
    this.status = status;
    this.error = error;
    this.message = message;
    this.path = path;
  }
}
