package com.taptrack.containermeasureservice.exceptions.dto;

import java.time.LocalDateTime;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 20/10/2025
 */
public record ApiErrorResponse(
  int status,
  String error,
  String message,
  String path,
  LocalDateTime timestamp
) {
}
