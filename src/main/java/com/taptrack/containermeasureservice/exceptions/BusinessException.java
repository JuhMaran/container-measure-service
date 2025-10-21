package com.taptrack.containermeasureservice.exceptions;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
public class BusinessException extends RuntimeException {
  public BusinessException(String message) {
    super(message);
  }
}
