package com.taptrack.containermeasureservice.application.dto.response;

import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
@Builder
@Schema(name = "ContainerMeasureResponseDTO", description = "Representa o retorno da medida de um recipiente, incluindo categoria, tipo, volume e metadados de criação/atualização.")
public record ContainerMeasureResponseDTO(
  @Schema(description = "Identificador único da medida de recipiente", example = "1")
  Long id,

  @Schema(description = "Categoria do recipiente", example = "GLASS")
  ContainerCategory category,

  @Schema(description = "Tipo do recipiente", example = "PINT")
  ContainerType type,

  @Schema(description = "Volume em mililitros do recipiente", example = "473")
  Integer volumeMl,

  @Schema(description = "Descrição do recipiente", example = "Copo americano padrão 16 oz")
  String description,

  @Schema(description = "Indica se o recipiente está ativo", example = "true")
  Boolean active,

  @Schema(description = "Data e hora de criação do registro", example = "2025-10-21T10:00:00")
  LocalDateTime createdDate,

  @Schema(description = "Data e hora da última atualização do registro", example = "2025-10-21T12:00:00")
  LocalDateTime updateDate
) {
}
