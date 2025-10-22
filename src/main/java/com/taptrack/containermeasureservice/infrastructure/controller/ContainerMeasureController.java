package com.taptrack.containermeasureservice.infrastructure.controller;

import com.taptrack.containermeasureservice.application.dto.request.ContainerMeasureFilterDTO;
import com.taptrack.containermeasureservice.application.dto.request.PaginationDTO;
import com.taptrack.containermeasureservice.application.dto.request.VolumeRangeDTO;
import com.taptrack.containermeasureservice.application.dto.response.ContainerMeasureResponseDTO;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import com.taptrack.containermeasureservice.infrastructure.service.ContainerMeasureQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/container-measures")
@CrossOrigin("http://localhost:4200")
public class ContainerMeasureController {

  private final ContainerMeasureQueryService queryService;

  /**
   * Endpoint POST - Pesquisa avançada com filtros complexos
   */
  @PostMapping("/search")
  public Page<ContainerMeasureResponseDTO> listMeasures(@RequestBody ContainerMeasureFilterDTO filter) {
    return queryService.listMeasures(filter);
  }

  /**
   * Endpoint GET - Filtro simples via query params (para uso direto no navegador/Postman)
   */
  @GetMapping
  public Page<ContainerMeasureResponseDTO> getContainerMeasures(
    @RequestParam(required = false) ContainerCategory category,
    @RequestParam(required = false) ContainerType type,
    @RequestParam(required = false, defaultValue = "true") Boolean active,
    @RequestParam(required = false) Integer volume,
    @RequestParam(required = false) Integer minVolume,
    @RequestParam(required = false) Integer maxVolume,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size
  ) {
    var filter = ContainerMeasureFilterDTO.builder()
      .category(category)
      .type(type)
      .active(active)
      .volumeRange(VolumeRangeDTO.builder()
        .exactVolume(volume)
        .minVolume(minVolume)
        .maxVolume(maxVolume)
        .build())
      .pagination(PaginationDTO.builder()
        .page(page)
        .size(size)
        .build())
      .build();

    return queryService.listMeasures(filter);
  }

}
