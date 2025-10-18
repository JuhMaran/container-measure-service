-- =========================================
-- Script Flyway: H2 - Container Measure Table
-- =========================================

DROP TABLE IF EXISTS container_measure;

CREATE TABLE container_measure (
    id IDENTITY PRIMARY KEY,
    version INT DEFAULT 0,
    category VARCHAR(30) NOT NULL,
    type VARCHAR(30) NOT NULL,
    volume_ml INT NOT NULL,
    description VARCHAR(255),
    active BOOLEAN DEFAULT TRUE,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================
-- INSERTS - GLASS TYPES
-- =========================================
INSERT INTO container_measure (category, type, volume_ml, description)
VALUES
('GLASS', 'TASTER', 100, 'Copo degustação pequeno'),
('GLASS', 'HALF', 250, 'Copo pequeno de 250 ml'),
('GLASS', 'PINT', 473, 'Copo americano padrão 16 oz'),
('GLASS', 'IMPERIAL_PINT', 568, 'Pint imperial britânico (20 oz)'),
('GLASS', 'TULIP', 330, 'Copo tipo tulipa para cervejas belgas'),
('GLASS', 'SNIFTER', 300, 'Copo globo para cervejas encorpadas'),
('GLASS', 'WEIZEN', 500, 'Copo alto para cervejas de trigo'),
('GLASS', 'STEIN', 1000, 'Caneca tradicional alemã de 1 litro');

-- =========================================
-- INSERTS - BOTTLES
-- =========================================
INSERT INTO container_measure (category, type, volume_ml, description)
VALUES
('BOTTLE', 'STUBBY', 330, 'Garrafa padrão 330 ml'),
('BOTTLE', 'BOMBER', 650, 'Garrafa 650 ml'),
('BOTTLE', 'CROWLER', 950, 'Lata grande de 950 ml (crowler)'),
('BOTTLE', 'HOWLER', 950, 'Garrafa intermediária de 950 ml'),
('BOTTLE', 'GROWLER', 1890, 'Garrafa refill de 1.89 litros');

-- =========================================
-- INSERTS - KEGS (Barril)
-- =========================================
INSERT INTO container_measure (category, type, volume_ml, description)
VALUES
('KEG', 'MINI_KEG', 5000, 'Mini barril doméstico 5L'),
('KEG', 'GROWLER', 2000, 'Growler pressurizado 2L'),
('KEG', 'CROWLER', 950, 'Lata pressurizada 950 ml para chope artesanal');
