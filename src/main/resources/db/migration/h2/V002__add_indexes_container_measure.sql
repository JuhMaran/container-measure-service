-- =========================================
-- Script Flyway: H2 - Add Indexes to Container Measure
-- Version: V2
-- =========================================

-- Verifica se os índices já existem antes de criar (H2 ignora caso já existam)
CREATE INDEX IF NOT EXISTS idx_container_category ON container_measure (category);
CREATE INDEX IF NOT EXISTS idx_container_type ON container_measure (type);
CREATE INDEX IF NOT EXISTS idx_container_active ON container_measure (active);

-- Índice composto para consultas com múltiplos filtros
CREATE INDEX IF NOT EXISTS idx_container_category_type_active
  ON container_measure (category, type, active);

-- Log simbólico
-- INFO: Índices criados para otimizar consultas combinadas em container_measure.
