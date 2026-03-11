-- ============================================================
-- Script de Criação do Schema - RAS Tarifas de Água API
-- Banco de Dados: PostgreSQL 14+
-- ============================================================

-- Cria o banco de dados (execute como superusuário, fora de uma transação)
-- CREATE DATABASE ras_tarifas;
-- \c ras_tarifas

-- ============================================================
-- Tabela: tabela_tarifaria
-- Entidade principal que representa uma tabela tarifária completa
-- ============================================================
CREATE TABLE IF NOT EXISTS tabela_tarifaria (
    id                   BIGSERIAL PRIMARY KEY,
    nome                 VARCHAR(100)  NOT NULL,
    descricao            VARCHAR(500),
    data_vigencia_inicio DATE,
    data_vigencia_fim    DATE,
    criado_em            TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Constraint: data_vigencia_inicio deve ser anterior a data_vigencia_fim
    CONSTRAINT chk_vigencia CHECK (
        data_vigencia_fim IS NULL OR data_vigencia_inicio <= data_vigencia_fim
    )
);

COMMENT ON TABLE tabela_tarifaria IS 'Tabela tarifária completa com todas as categorias e faixas de consumo';
COMMENT ON COLUMN tabela_tarifaria.nome IS 'Nome descritivo da tabela (ex: Tabela 2024)';
COMMENT ON COLUMN tabela_tarifaria.data_vigencia_inicio IS 'Data de início da vigência';
COMMENT ON COLUMN tabela_tarifaria.data_vigencia_fim IS 'Data de fim da vigência (NULL = indefinido)';

-- ============================================================
-- Tabela: categoria_tarifaria
-- Vínculo entre tabela tarifária e categoria de consumidor
-- ============================================================
CREATE TABLE IF NOT EXISTS categoria_tarifaria (
    id                  BIGSERIAL PRIMARY KEY,
    tabela_tarifaria_id BIGINT       NOT NULL,
    categoria           VARCHAR(20)  NOT NULL,

    -- Chave estrangeira: vincula à tabela tarifária pai
    CONSTRAINT fk_categoria_tabela
        FOREIGN KEY (tabela_tarifaria_id)
        REFERENCES tabela_tarifaria(id)
        ON DELETE CASCADE,

    -- Constraint: não permite categoria duplicada na mesma tabela
    CONSTRAINT uk_tabela_categoria
        UNIQUE (tabela_tarifaria_id, categoria),

    -- Constraint: valida os valores permitidos para categoria
    CONSTRAINT chk_categoria
        CHECK (categoria IN ('COMERCIAL', 'INDUSTRIAL', 'PARTICULAR', 'PUBLICO'))
);

COMMENT ON TABLE categoria_tarifaria IS 'Vínculo entre tabela tarifária e categoria de consumidor';
COMMENT ON COLUMN categoria_tarifaria.categoria IS 'Tipo de consumidor: COMERCIAL, INDUSTRIAL, PARTICULAR ou PUBLICO';

-- ============================================================
-- Tabela: faixa_consumo
-- Faixas de consumo parametrizadas por categoria
-- ============================================================
CREATE TABLE IF NOT EXISTS faixa_consumo (
    id                    BIGSERIAL      PRIMARY KEY,
    categoria_tarifaria_id BIGINT        NOT NULL,
    inicio                INTEGER        NOT NULL,
    fim                   INTEGER        NOT NULL,
    valor_unitario        NUMERIC(10, 4) NOT NULL,

    -- Chave estrangeira: vincula à categoria tarifária pai
    CONSTRAINT fk_faixa_categoria
        FOREIGN KEY (categoria_tarifaria_id)
        REFERENCES categoria_tarifaria(id)
        ON DELETE CASCADE,

    -- Constraint: início deve ser menor que fim
    CONSTRAINT chk_faixa_ordem
        CHECK (inicio < fim),

    -- Constraint: início não pode ser negativo
    CONSTRAINT chk_faixa_inicio
        CHECK (inicio >= 0),

    -- Constraint: valor unitário deve ser positivo
    CONSTRAINT chk_valor_unitario
        CHECK (valor_unitario > 0)
);

COMMENT ON TABLE faixa_consumo IS 'Faixas de consumo parametrizadas com valor unitário por m³';
COMMENT ON COLUMN faixa_consumo.inicio IS 'Início da faixa em m³ (inclusivo)';
COMMENT ON COLUMN faixa_consumo.fim IS 'Fim da faixa em m³ (inclusivo)';
COMMENT ON COLUMN faixa_consumo.valor_unitario IS 'Valor em R$ por m³ consumido nesta faixa';

-- ============================================================
-- Índices para otimização de consultas
-- ============================================================

-- Índice para busca de categorias por tabela
CREATE INDEX IF NOT EXISTS idx_categoria_tabela
    ON categoria_tarifaria(tabela_tarifaria_id);

-- Índice para busca de faixas por categoria
CREATE INDEX IF NOT EXISTS idx_faixa_categoria
    ON faixa_consumo(categoria_tarifaria_id);

-- Índice para busca de faixas ordenadas por início (usado no cálculo progressivo)
CREATE INDEX IF NOT EXISTS idx_faixa_inicio
    ON faixa_consumo(categoria_tarifaria_id, inicio ASC);
