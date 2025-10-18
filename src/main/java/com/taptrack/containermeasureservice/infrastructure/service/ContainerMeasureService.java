package com.taptrack.containermeasureservice.infrastructure.service;

import com.taptrack.containermeasureservice.application.dto.request.ContainerMeasureRequestDTO;
import com.taptrack.containermeasureservice.application.dto.response.ContainerMeasureResponseDTO;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;

import java.util.List;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
public interface ContainerMeasureService {

  ContainerMeasureResponseDTO create(ContainerMeasureRequestDTO dto);

  ContainerMeasureResponseDTO update(Long id, ContainerMeasureRequestDTO dto);

  void delete(Long id);

  ContainerMeasureResponseDTO findById(Long id);

  List<ContainerMeasureResponseDTO> findAll();

  List<ContainerMeasureResponseDTO> findByCategory(ContainerCategory category);

  List<ContainerMeasureResponseDTO> findByType(ContainerType type);

}
