package com.taptrack.containermeasureservice.domain.repository;

import com.taptrack.containermeasureservice.domain.model.ContainerMeasure;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
@Repository
public interface ContainerMeasureRepository extends JpaRepository<ContainerMeasure, Long> {

  List<ContainerMeasure> findByCategory(ContainerCategory category);

  List<ContainerMeasure> findByType(ContainerType type);

  List<ContainerMeasure> findByActiveTrue();

}
