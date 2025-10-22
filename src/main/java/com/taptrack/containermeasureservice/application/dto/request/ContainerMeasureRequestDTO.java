package com.taptrack.containermeasureservice.application.dto.request;

import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
@Schema(
  name = "ContainerMeasureRequestDTO",
  description = "DTO usado para criar ou atualizar medidas de recipientes."
)
public record ContainerMeasureRequestDTO(

  @Schema(
    description = "Categoria do recipiente",
    example = "GLASS",
    requiredMode = Schema.RequiredMode.REQUIRED,
    enumAsRef = true
  )
  @NotNull(message = "A categoria do recipiente é obrigatória.")
  ContainerCategory category,

  @Schema(
    description = "Tipo do recipiente",
    example = "PINT",
    requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotNull(message = "O tipo de recipiente é obrigatório.")
  ContainerType type,

  @Schema(
    description = "Volume em mililitros do recipiente",
    example = "473",
    requiredMode = Schema.RequiredMode.REQUIRED
  )
  @NotNull(message = "O volume do recipiente é obrigatório.")
  @Positive(message = "O volume deve ser um número positivo em mililitros.")
  Integer volumeMl,

  @Schema(
    description = "Descrição do recipiente",
    example = "Copo americano padrão 16 oz",
    maxLength = 255
  )
  @Size(max = 255, message = "A descrição deve conter no máximo 255 caracteres.")
  String description
) {
}
