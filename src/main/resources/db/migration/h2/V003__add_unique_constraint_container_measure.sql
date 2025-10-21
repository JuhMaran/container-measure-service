-- =========================================
-- Script Flyway: H2 - Add Unique Constraint
-- Version: V3
-- =========================================

-- Adiciona constraint única para evitar duplicidade de medidas
ALTER TABLE container_measure
ADD CONSTRAINT uq_container_category_type_volume UNIQUE (category, type, volume_ml);

-- INFO: Constraint adicionada para garantir unicidade de category + type + volume_ml
