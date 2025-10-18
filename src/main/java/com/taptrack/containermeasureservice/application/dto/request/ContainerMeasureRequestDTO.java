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
  @NotNull(message = "Campo categoria é obrigatório")
  ContainerCategory category,

  @NotNull(message = "Campo tipo é obrigatório")
  ContainerType type,

  @NotNull(message = "Campo volume em ml é obrigatório")
  @Positive(message = "Deve informar um valor positivo acima de zero")
  Integer volumeMl,

  @Size(max = 255, message = "Deve conter no máximo 255 caracteres")
  String description
) {
}
