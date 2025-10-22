package com.taptrack.containermeasureservice.infrastructure.service.impl;

import com.taptrack.containermeasureservice.application.dto.request.ContainerMeasureFilterDTO;
import com.taptrack.containermeasureservice.application.dto.response.ContainerMeasureResponseDTO;
import com.taptrack.containermeasureservice.application.mapper.ContainerMeasureMapper;
import com.taptrack.containermeasureservice.domain.model.ContainerMeasure;
import com.taptrack.containermeasureservice.domain.repository.ContainerMeasureRepository;
import com.taptrack.containermeasureservice.domain.specification.ContainerMeasureSpecification;
import com.taptrack.containermeasureservice.infrastructure.service.ContainerMeasureQueryService;
import jakarta.ws.rs.InternalServerErrorException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 20/10/2025
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ContainerMeasureQueryServiceImpl implements ContainerMeasureQueryService {

  private static final Logger log = LoggerFactory.getLogger(ContainerMeasureQueryServiceImpl.class);

  ContainerMeasureMapper mapper;
  ContainerMeasureRepository repository;

  @Override
  public Page<ContainerMeasureResponseDTO> listMeasures(ContainerMeasureFilterDTO filter) {
    try {
      log.info("Iniciando listagem de medidas. Página: {}, Tamanho: {}",
        filter.pagination().page(), filter.pagination().size());

      var volumeRange = filter.volumeRange();

      Specification<ContainerMeasure> spec = Specification.allOf(
        ContainerMeasureSpecification.categoryEquals(filter.category()),
        ContainerMeasureSpecification.typeEquals(filter.type()),
        ContainerMeasureSpecification.activeEquals(filter.isActive()),
        ContainerMeasureSpecification.volumeEquals(volumeRange.exactVolume()),
        ContainerMeasureSpecification.volumeBetween(volumeRange.minVolume(), volumeRange.maxVolume())
      );

      var pageable = filter.pagination().toPageable();
      var result = repository.findAll(spec, pageable);

      log.info("Listagem concluída. Total de registros encontrados: {}", result.getTotalElements());
      return result.map(mapper::toResponse);
    } catch (Exception e) {
      log.error("Erro ao listar medidas: {}", e.getMessage(), e);
      throw new InternalServerErrorException("Não foi possível listar as medidas de recipientes. Tente novamente mais tarde.");
    }
  }

}
