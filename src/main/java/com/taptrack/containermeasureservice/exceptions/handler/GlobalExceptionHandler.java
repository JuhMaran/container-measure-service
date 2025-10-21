package com.taptrack.containermeasureservice.exceptions.handler;

import com.taptrack.containermeasureservice.exceptions.*;
import com.taptrack.containermeasureservice.exceptions.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 20/10/2025
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiErrorResponse> handleBusinessException(BusinessException ex, WebRequest request) {
    return buildResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ApiErrorResponse> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {
    return buildResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), request);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<ApiErrorResponse> handleForbiddenException(ForbiddenException ex, WebRequest request) {
    return buildResponse(HttpStatus.FORBIDDEN, ex.getMessage(), request);
  }

  @ExceptionHandler(ResourceConflictException.class)
  public ResponseEntity<ApiErrorResponse> handleConflictException(ResourceConflictException ex, WebRequest request) {
    return buildResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ApiErrorResponse> handleGenericException(RuntimeException ex, WebRequest request) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor.", request);
  }

  private ResponseEntity<ApiErrorResponse> buildResponse(HttpStatus status,
                                                         String message, WebRequest request) {
    var response = new ApiErrorResponse(
      status.value(),
      status.getReasonPhrase(),
      message,
      request.getDescription(false)
        .replace("uri=", ""),
      LocalDateTime.now()
    );
    return ResponseEntity.status(status).body(response);
  }

}
