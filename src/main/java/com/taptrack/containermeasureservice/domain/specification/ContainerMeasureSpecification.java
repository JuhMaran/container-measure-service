package com.taptrack.containermeasureservice.domain.specification;

import com.taptrack.containermeasureservice.domain.model.ContainerMeasure;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

/**
 * Specification para filtros dinâmicos
 *
 * @author Juliane Maran
 * @since 20/10/2025
 */
@Slf4j
public class ContainerMeasureSpecification {

  private ContainerMeasureSpecification() {
  }

  public static Specification<ContainerMeasure> categoryEquals(ContainerCategory category) {
    return (root, query, criteriaBuilder) ->
      category != null ? criteriaBuilder.equal(root.get("category"), category) : null;
  }

  public static Specification<ContainerMeasure> typeEquals(ContainerType type) {
    return (root, query, criteriaBuilder) ->
      type != null ? criteriaBuilder.equal(root.get("type"), type) : null;
  }

  public static Specification<ContainerMeasure> activeEquals(Boolean active) {
    return (root, query, criteriaBuilder) ->
      active != null ? criteriaBuilder.equal(root.get("active"), active) : null;
  }

  public static Specification<ContainerMeasure> volumeEquals(Integer volume) {
    return (root, query, criteriaBuilder) ->
      volume != null ? criteriaBuilder.equal(root.get("volumeMl"), volume) : null;
  }

  public static Specification<ContainerMeasure> volumeBetween(Integer min, Integer max) {
    return (root, query, criteriaBuilder) -> {
      if (min != null && max != null)
        return criteriaBuilder.between(root.get("volumeMl"), min, max);
      if (min != null)
        return criteriaBuilder.greaterThanOrEqualTo(root.get("volumeMl"), min);
      if (max != null)
        return criteriaBuilder.lessThanOrEqualTo(root.get("volumeMl"), max);
      return null;
    };

  }

}
