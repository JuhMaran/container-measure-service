package com.taptrack.containermeasureservice.application.dto.request;

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
  name = "VolumeRangeDTO",
  description = "DTO que define uma faixa de volume para filtragem, podendo especificar volume exato, mínimo e máximo."
)
public record VolumeRangeDTO(
  @Schema(description = "Volume exato em mililitros", example = "473")
  Integer exactVolume,

  @Schema(description = "Volume mínimo em mililitros", example = "200")
  Integer minVolume,

  @Schema(description = "Volume máximo em mililitros", example = "1000")
  Integer maxVolume
) {
}
