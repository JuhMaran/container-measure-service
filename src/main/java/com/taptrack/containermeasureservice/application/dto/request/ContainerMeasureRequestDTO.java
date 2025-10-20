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

  @NotNull(message = "A categoria do recipiente é obrigatória.")
  ContainerCategory category,

  @NotNull(message = "O tipo de recipiente é obrigatório.")
  ContainerType type,

  @NotNull(message = "O volume do recipiente é obrigatório.")
  @Positive(message = "O volume deve ser um número positivo em mililitros.")
  Integer volumeMl,

  @Size(max = 255, message = "A descrição deve conter no máximo 255 caracteres.")
  String description
) {
}
