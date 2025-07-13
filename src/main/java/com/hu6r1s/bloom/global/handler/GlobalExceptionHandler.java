package com.hu6r1s.bloom.global.handler;

import com.hu6r1s.bloom.global.exception.ChatPartnerNotFoundException;
import com.hu6r1s.bloom.global.exception.ErrorResponse;
import com.hu6r1s.bloom.global.exception.LikeDuplicationException;
import com.hu6r1s.bloom.global.exception.NotFoundChatRoomException;
import com.hu6r1s.bloom.global.exception.NotFoundImageException;
import com.hu6r1s.bloom.global.exception.ProfileDuplicationException;
import com.hu6r1s.bloom.global.exception.ProfileNotfoundException;
import com.hu6r1s.bloom.global.exception.SelfLikeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({ SelfLikeException.class })
  public ResponseEntity<ErrorResponse> handleLikeConflict(SelfLikeException ex, WebRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    log.warn("Bad Request exception occurred: {}", ex.getMessage());

    ErrorResponse errorResponse = new ErrorResponse(
        status.value(),
        status.getReasonPhrase(),
        ex.getMessage(),
        request.getDescription(false).replace("uri=", "")
    );

    return new ResponseEntity<>(errorResponse, status);
  }

  @ExceptionHandler({ LikeDuplicationException.class, ProfileDuplicationException.class })
  public ResponseEntity<ErrorResponse> handleLikeConflict(RuntimeException  ex, WebRequest request) {
    HttpStatus status = HttpStatus.CONFLICT;
    log.warn("Conflict exception occurred: {}", ex.getMessage());

    ErrorResponse errorResponse = new ErrorResponse(
        status.value(),
        status.getReasonPhrase(),
        ex.getMessage(),
        request.getDescription(false).replace("uri=", "")
    );

    return new ResponseEntity<>(errorResponse, status);
  }

  @ExceptionHandler({ NotFoundChatRoomException.class })
  public ResponseEntity<ErrorResponse> handleLikeConflict(NotFoundChatRoomException ex, WebRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    log.warn("Internal Server Error exception occurred: {}", ex.getMessage());

    ErrorResponse errorResponse = new ErrorResponse(
        status.value(),
        status.getReasonPhrase(),
        ex.getMessage(),
        request.getDescription(false).replace("uri=", "")
    );

    return new ResponseEntity<>(errorResponse, status);
  }

  @ExceptionHandler({ ChatPartnerNotFoundException.class })
  public ResponseEntity<ErrorResponse> handleLikeConflict(ChatPartnerNotFoundException ex, WebRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    log.warn("Internal Server Error exception occurred: {}", ex.getMessage());

    ErrorResponse errorResponse = new ErrorResponse(
        status.value(),
        status.getReasonPhrase(),
        ex.getMessage(),
        request.getDescription(false).replace("uri=", "")
    );

    return new ResponseEntity<>(errorResponse, status);
  }

  @ExceptionHandler({ ProfileNotfoundException.class })
  public ResponseEntity<ErrorResponse> handleLikeConflict(ProfileNotfoundException ex, WebRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    log.warn("Internal Server Error exception occurred: {}", ex.getMessage());

    ErrorResponse errorResponse = new ErrorResponse(
        status.value(),
        status.getReasonPhrase(),
        ex.getMessage(),
        request.getDescription(false).replace("uri=", "")
    );

    return new ResponseEntity<>(errorResponse, status);
  }

  @ExceptionHandler({ NotFoundImageException.class })
  public ResponseEntity<ErrorResponse> handleLikeConflict(NotFoundImageException ex, WebRequest request) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    log.warn("Internal Server Error exception occurred: {}", ex.getMessage());

    ErrorResponse errorResponse = new ErrorResponse(
        status.value(),
        status.getReasonPhrase(),
        ex.getMessage(),
        request.getDescription(false).replace("uri=", "")
    );

    return new ResponseEntity<>(errorResponse, status);
  }
}
