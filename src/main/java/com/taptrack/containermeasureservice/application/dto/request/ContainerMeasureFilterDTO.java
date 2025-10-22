package com.taptrack.containermeasureservice.application.dto.request;

import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 21/10/2025
 */
@Builder
@Schema(
  name = "ContainerMeasureFilterDTO",
  description = "DTO usado para filtrar as medidas de recipientes com base em critérios como categoria, tipo, volume e status ativo."
)
public record ContainerMeasureFilterDTO(
  @Schema(description = "Categoria do recipiente a filtrar", example = "GLASS")
  ContainerCategory category,

  @Schema(description = "Tipo do recipiente a filtrar", example = "PINT")
  ContainerType type,

  @Schema(description = "Indica se o recipiente está ativo (padrão: true)", example = "true")
  Boolean active,

  @Schema(description = "Faixa de volume em mililitros para filtro", implementation = VolumeRangeDTO.class)
  VolumeRangeDTO volumeRange,

  @Schema(description = "Informações de paginação", implementation = PaginationDTO.class)
  PaginationDTO pagination
) {
  public boolean isActive() {
    // Define o padrão (default) para evitar literal booleana desnecessária
    return active == null || active;
  }

}
