-- LIMPEZA (CASO EXISTAM)

DROP TABLE IF EXISTS faixa_consumo CASCADE;
DROP TABLE IF EXISTS categoria_tarifaria CASCADE;
DROP TABLE IF EXISTS tabela_tarifaria CASCADE;

-- TABELA TARIFARIA

CREATE TABLE tabela_tarifaria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(255),
    data_vigencia DATE NOT NULL
);


-- CATEGORIA TARIFARIA

CREATE TABLE categoria_tarifaria (
    id SERIAL PRIMARY KEY,
    tabela_tarifaria_id INTEGER NOT NULL,
    categoria VARCHAR(50) NOT NULL,

    CONSTRAINT fk_tabela_tarifaria
        FOREIGN KEY (tabela_tarifaria_id)
        REFERENCES tabela_tarifaria(id)
);


-- FAIXA DE CONSUMO

CREATE TABLE faixa_consumo (
    id SERIAL PRIMARY KEY,
    categoria_tarifaria_id INTEGER NOT NULL,
    consumo_minimo INTEGER NOT NULL,
    consumo_maximo INTEGER NOT NULL,
    valor_tarifa NUMERIC(10,2) NOT NULL,

    CONSTRAINT fk_categoria_tarifaria
        FOREIGN KEY (categoria_tarifaria_id)
        REFERENCES categoria_tarifaria(id)
);


-- INDICES PARA PERFORMANCE

CREATE INDEX idx_categoria
ON categoria_tarifaria(categoria);

CREATE INDEX idx_faixa_categoria
ON faixa_consumo(categoria_tarifaria_id);

-- TABELA TARIFARIA

INSERT INTO tabela_tarifaria
(id, nome, descricao, data_vigencia)
VALUES
(1, 'Tabela Tarifária 2026', 'Tabela oficial de tarifas de água', '2026-01-01');

-- CATEGORIAS

INSERT INTO categoria_tarifaria
(id, tabela_tarifaria_id, categoria)
VALUES
(1, 1, 'COMERCIAL'),
(2, 1, 'INDUSTRIAL'),
(3, 1, 'PARTICULAR'),
(4, 1, 'PUBLICO');

-- FAIXAS COMERCIAL

INSERT INTO faixa_consumo
(categoria_tarifaria_id, consumo_minimo, consumo_maximo, valor_tarifa)
VALUES
(1, 0, 10, 2.50),
(1, 11, 20, 3.75),
(1, 21, 30, 5.00),
(1, 31, 99999, 7.50);

-- FAIXAS INDUSTRIAL

INSERT INTO faixa_consumo
(categoria_tarifaria_id, consumo_minimo, consumo_maximo, valor_tarifa)
VALUES
(2, 0, 10, 1.00),
(2, 11, 20, 2.00),
(2, 21, 30, 3.00),
(2, 31, 99999, 4.00);

-- FAIXAS PARTICULAR

INSERT INTO faixa_consumo
(categoria_tarifaria_id, consumo_minimo, consumo_maximo, valor_tarifa)
VALUES
(3, 0, 10, 1.50),
(3, 11, 20, 2.25),
(3, 21, 30, 3.00),
(3, 31, 99999, 4.50);

-- FAIXAS PUBLICO

INSERT INTO faixa_consumo
(categoria_tarifaria_id, consumo_minimo, consumo_maximo, valor_tarifa)
VALUES
(4, 0, 10, 0.80),
(4, 11, 20, 1.20),
(4, 21, 30, 1.80),
(4, 31, 99999, 2.50);