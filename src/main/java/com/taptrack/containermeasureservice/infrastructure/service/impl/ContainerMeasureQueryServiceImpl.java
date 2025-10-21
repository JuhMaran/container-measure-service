package com.taptrack.containermeasureservice.infrastructure.service.impl;

import com.taptrack.containermeasureservice.application.dto.request.ContainerMeasureFilterDTO;
import com.taptrack.containermeasureservice.application.dto.response.ContainerMeasureResponseDTO;
import com.taptrack.containermeasureservice.application.mapper.ContainerMeasureMapper;
import com.taptrack.containermeasureservice.domain.model.ContainerMeasure;
import com.taptrack.containermeasureservice.domain.repository.ContainerMeasureRepository;
import com.taptrack.containermeasureservice.domain.specification.ContainerMeasureSpecification;
import com.taptrack.containermeasureservice.infrastructure.service.ContainerMeasureQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 20/10/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ContainerMeasureQueryServiceImpl implements ContainerMeasureQueryService {

  private final ContainerMeasureMapper mapper;
  private final ContainerMeasureRepository repository;

  @Override
  public Page<ContainerMeasureResponseDTO> listMeasures(ContainerMeasureFilterDTO filter) {
    try {
      log.info("Iniciando listagem de medidas de recipientes. Página: {}, Tamanho: {}", filter.page(), filter.size());

      Specification<ContainerMeasure> spec = Specification.allOf(
        ContainerMeasureSpecification.categoryEquals(filter.category()),
        ContainerMeasureSpecification.typeEquals(filter.type()),
        ContainerMeasureSpecification.activeEquals(filter.active() != null ? filter.active() : true),
        ContainerMeasureSpecification.volumeEquals(filter.volume()),
        ContainerMeasureSpecification.volumeBetween(filter.minVolume(), filter.maxVolume())
      );
      Pageable pageable = PageRequest.of(filter.page(), filter.size(), Sort.by("category", "type", "volumeMl").ascending());
      Page<ContainerMeasure> result = repository.findAll(spec, pageable);

      log.info("Listagem concluída. Total de registros encontrados: {}", result.getTotalElements());
      return result.map(mapper::toResponse);
    } catch (Exception e) {
      log.error("Erro ao listar medidas de recipientes: {}", e.getMessage(), e);
      throw new RuntimeException("Não foi possível listar as medidas de recipientes. Tente novamente mais tarde.");
    }
  }

}
