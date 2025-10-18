package com.taptrack.containermeasureservice.domain.model;

import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
@Entity
@Table(name = "container_measure")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContainerMeasure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Version
  private Integer version;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 30)
  private ContainerCategory category; // GLASS, BOTTLE, GROWLER, ETC

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 30)
  private ContainerType type; // PINT, TASTER, GROWLER, ETC

  @Column(nullable = false)
  private Integer volumeMl;

  @Column(length = 255)
  private String description;

  @Column(nullable = false)
  private Boolean active = true;

  @Column(updatable = false)
  private LocalDateTime createdDate = LocalDateTime.now();

  private LocalDateTime updateDate = LocalDateTime.now();

}
