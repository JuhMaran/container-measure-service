package com.taptrack.containermeasureservice.application.mapper;

import com.taptrack.containermeasureservice.application.dto.request.ContainerMeasureRequestDTO;
import com.taptrack.containermeasureservice.application.dto.response.ContainerMeasureResponseDTO;
import com.taptrack.containermeasureservice.domain.model.ContainerMeasure;
import org.mapstruct.*;

import java.util.List;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
@Mapper(componentModel = "spring")
public interface ContainerMeasureMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "active", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "updateDate", ignore = true)
  ContainerMeasure toEntity(ContainerMeasureRequestDTO dto);

  ContainerMeasureResponseDTO toResponse(ContainerMeasure entity);

  List<ContainerMeasureResponseDTO> toResponseList(List<ContainerMeasure> entities);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "version", ignore = true)
  @Mapping(target = "active", ignore = true)
  @Mapping(target = "createdDate", ignore = true)
  @Mapping(target = "updateDate", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateEntityFromDto(ContainerMeasureRequestDTO dto, @MappingTarget ContainerMeasure entity);

}
