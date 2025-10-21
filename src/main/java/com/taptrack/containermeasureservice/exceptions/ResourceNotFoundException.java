package com.taptrack.containermeasureservice.exceptions;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 20/10/2025
 */
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
