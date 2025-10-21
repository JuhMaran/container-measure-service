package com.taptrack.containermeasureservice.exceptions;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 20/10/2025
 */
public class UnauthorizedException extends RuntimeException {
  public UnauthorizedException(String message) {
    super(message);
  }
}
