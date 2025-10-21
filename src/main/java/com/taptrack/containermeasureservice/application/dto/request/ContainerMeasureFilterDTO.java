package com.taptrack.containermeasureservice.application.dto.request;

import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import lombok.Builder;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 20/10/2025
 */
@Builder
public record ContainerMeasureFilterDTO(
  ContainerCategory category,
  ContainerType type,
  Boolean active,
  Integer volume,
  Integer minVolume,
  Integer maxVolume,
  int page,
  int size
) {
}
