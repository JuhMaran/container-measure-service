package com.taptrack.containermeasureservice.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @param page Página da lista (default 0)
 * @param size Tamanho da página (default 10)
 * @author Juliane Maran
 * @since 20/10/2025
 */
@Builder
@Schema(
  name = "PaginationDTO",
  description = "DTO que representa informações de paginação para requisições paginadas."
)
public record PaginationDTO(
  @Schema(description = "Número da página (inicia em 0)", example = "0", minimum = "0")
  @Min(value = 0, message = "O número da página deve ser maior ou igual a zero.")
  int page,

  @Schema(description = "Quantidade de registros por página (mínimo 1)", example = "10", minimum = "1")
  @Min(value = 1, message = "O tamanho da página deve ser pelo menos 1.")
  int size
) {

  public Pageable toPageable() {
    return PageRequest.of(page, size, Sort.by("category", "type", "volumeMl"));
  }

}
