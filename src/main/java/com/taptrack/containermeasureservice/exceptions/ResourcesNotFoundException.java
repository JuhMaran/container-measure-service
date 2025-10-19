package com.taptrack.containermeasureservice.exceptions;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
public class ResourcesNotFoundException extends RuntimeException {
  public ResourcesNotFoundException(String message) {
    super(message);
  }
}
