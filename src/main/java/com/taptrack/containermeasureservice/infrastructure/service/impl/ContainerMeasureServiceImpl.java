package com.taptrack.containermeasureservice.infrastructure.service.impl;

import com.taptrack.containermeasureservice.application.dto.request.ContainerMeasureRequestDTO;
import com.taptrack.containermeasureservice.application.dto.response.ContainerMeasureResponseDTO;
import com.taptrack.containermeasureservice.application.mapper.ContainerMeasureMapper;
import com.taptrack.containermeasureservice.domain.model.ContainerMeasure;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import com.taptrack.containermeasureservice.domain.repository.ContainerMeasureRepository;
import com.taptrack.containermeasureservice.exceptions.ResourcesNotFoundException;
import com.taptrack.containermeasureservice.infrastructure.service.ContainerMeasureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ContainerMeasureServiceImpl implements ContainerMeasureService {

  // TODO: Incluir logs usando Slf4j
  // TODO: Incluir exceptions personalizadas usando a lib com.julianemaran.libraries.customexceptions:
  // Not Found (404), Bad Request (400), Conflict (409), Internal Server Error (500)
  // As mensagens de log e de erro devem estar em Português
  // TODO: Aplicar boas práticas, princípio SOLID, DRY (Don't Repeat Yourself)

  private final ContainerMeasureRepository repository;
  private final ContainerMeasureMapper mapper;

  @Override
  @Transactional
  public ContainerMeasureResponseDTO create(ContainerMeasureRequestDTO dto) {
    ContainerMeasure entity = mapper.toEntity(dto);
    return mapper.toResponse(repository.save(entity));
  }

  @Override
  @Transactional
  public ContainerMeasureResponseDTO update(Long id, ContainerMeasureRequestDTO dto) {
    ContainerMeasure entity = repository.findById(id)
      .orElseThrow(() -> new ResourcesNotFoundException("Container measure not found with ID: " + id));
    mapper.updateEntityFromDto(dto, entity);
    return mapper.toResponse(repository.save(entity));
  }

  @Override
  @Transactional
  public void delete(Long id) {
    repository.deleteById(id);
  }

  @Override
  @Transactional
  public ContainerMeasureResponseDTO findById(Long id) {
    return repository.findById(id)
      .map(mapper::toResponse)
      .orElseThrow(() -> new ResourcesNotFoundException("Container measure not found with ID: " + id));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ContainerMeasureResponseDTO> findAll() {
    return mapper.toResponseList(repository.findAll());
  }

  @Override
  @Transactional(readOnly = true)
  public List<ContainerMeasureResponseDTO> findByCategory(ContainerCategory category) {
    return mapper.toResponseList(repository.findByCategory(category));
  }

  @Override
  @Transactional(readOnly = true)
  public List<ContainerMeasureResponseDTO> findByType(ContainerType type) {
    return mapper.toResponseList(repository.findByType(type));
  }

}
