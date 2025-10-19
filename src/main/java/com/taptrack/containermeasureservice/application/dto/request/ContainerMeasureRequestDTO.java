package com.taptrack.containermeasureservice.application.dto.request;

import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
public record ContainerMeasureRequestDTO(
  // TODO: Incluir mensagens de validação nas anotações, em Português
  @NotNull
  ContainerCategory category,

  @NotNull
  ContainerType type,

  @NotNull
  @Positive
  Integer volumeMl,

  @Size(max = 255)
  String description
) {
}
