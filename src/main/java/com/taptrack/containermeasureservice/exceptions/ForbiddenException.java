package com.taptrack.containermeasureservice.exceptions;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 20/10/2025
 */
public class ForbiddenException extends RuntimeException {
  public ForbiddenException(String message) {
    super(message);
  }
}
