package com.taptrack.containermeasureservice.infrastructure.service;

import com.taptrack.containermeasureservice.application.dto.request.ContainerMeasureFilterDTO;
import com.taptrack.containermeasureservice.application.dto.response.ContainerMeasureResponseDTO;
import org.springframework.data.domain.Page;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 20/10/2025
 */
public interface ContainerMeasureQueryService {

  Page<ContainerMeasureResponseDTO> listMeasures(ContainerMeasureFilterDTO filter);

}
