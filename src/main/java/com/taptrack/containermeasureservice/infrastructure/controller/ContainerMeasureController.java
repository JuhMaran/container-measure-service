package com.taptrack.containermeasureservice.infrastructure.controller;

import com.taptrack.containermeasureservice.application.dto.request.ContainerMeasureRequestDTO;
import com.taptrack.containermeasureservice.application.dto.response.ContainerMeasureResponseDTO;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerCategory;
import com.taptrack.containermeasureservice.domain.model.enums.ContainerType;
import com.taptrack.containermeasureservice.infrastructure.service.ContainerMeasureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * container-measure-service
 *
 * @author Juliane Maran
 * @since 18/10/2025
 */
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/container-measures")
@RequiredArgsConstructor
public class ContainerMeasureController {

  private final ContainerMeasureService service;

  @PostMapping
  public ResponseEntity<ContainerMeasureResponseDTO> create(@Valid @RequestBody ContainerMeasureRequestDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ContainerMeasureResponseDTO> update(@PathVariable Long id,
                                                            @Valid @RequestBody ContainerMeasureRequestDTO dto) {
    return ResponseEntity.ok(service.update(id, dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ContainerMeasureResponseDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @GetMapping
  public ResponseEntity<List<ContainerMeasureResponseDTO>> findAll(
    @RequestParam(required = false) ContainerCategory category,
    @RequestParam(required = false) ContainerType type
  ) {
    if (category != null) return ResponseEntity.ok(service.findByCategory(category));
    if (type != null) return ResponseEntity.ok(service.findByType(type));
    return ResponseEntity.ok(service.findAll());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

}
