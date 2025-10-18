package com.taptrack.containermeasureservice.application.dto.response;

import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;

import java.time.LocalDateTime;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
public record ContainerMeasureResponseDTO(
  Long id,
  ContainerCategory category,
  ContainerType type,
  Integer volumeMl,
  String description,
  Boolean active,
  LocalDateTime createdDate,
  LocalDateTime updateDate
) {
}
