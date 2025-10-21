-- =========================================
-- Script Flyway: MySQL - Add Indexes to Container Measure
-- Version: V2
-- =========================================

-- Índices simples (verificação de duplicação automática pelo nome)
CREATE INDEX idx_container_category ON container_measure (category);
CREATE INDEX idx_container_type ON container_measure (type);
CREATE INDEX idx_container_active ON container_measure (active);

-- Índice composto para consultas frequentes
CREATE INDEX idx_container_category_type_active
  ON container_measure (category, type, active);

-- Comentário informativo
-- INFO: Índices adicionados para otimização de consultas combinadas (category, type, active).
