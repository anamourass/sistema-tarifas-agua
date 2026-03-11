-- ============================================================
-- Script de Dados de Exemplo (Seed) - RAS Tarifas de Água API
-- ============================================================
-- Este script insere uma tabela tarifária de exemplo com todas
-- as quatro categorias e quatro faixas de consumo cada.
-- ============================================================

BEGIN;

-- ============================================================
-- Tabela Tarifária de Exemplo
-- ============================================================
INSERT INTO tabela_tarifaria (nome, descricao, data_vigencia_inicio)
VALUES (
    'Tabela Tarifária 2024',
    'Tabela de tarifas de água vigente para o exercício de 2024',
    '2024-01-01'
);

-- Captura o ID da tabela recém-inserida
DO $$
DECLARE
    v_tabela_id         BIGINT;
    v_cat_comercial_id  BIGINT;
    v_cat_industrial_id BIGINT;
    v_cat_particular_id BIGINT;
    v_cat_publico_id    BIGINT;
BEGIN
    -- Obtém o ID da tabela inserida
    SELECT id INTO v_tabela_id
    FROM tabela_tarifaria
    WHERE nome = 'Tabela Tarifária 2024'
    ORDER BY id DESC LIMIT 1;

    -- ============================================================
    -- Categoria: COMERCIAL
    -- ============================================================
    INSERT INTO categoria_tarifaria (tabela_tarifaria_id, categoria)
    VALUES (v_tabela_id, 'COMERCIAL')
    RETURNING id INTO v_cat_comercial_id;

    INSERT INTO faixa_consumo (categoria_tarifaria_id, inicio, fim, valor_unitario) VALUES
        (v_cat_comercial_id,  0,    10, 2.50),
        (v_cat_comercial_id, 11,    20, 3.75),
        (v_cat_comercial_id, 21,    30, 5.00),
        (v_cat_comercial_id, 31, 99999, 7.50);

    -- ============================================================
    -- Categoria: INDUSTRIAL
    -- ============================================================
    INSERT INTO categoria_tarifaria (tabela_tarifaria_id, categoria)
    VALUES (v_tabela_id, 'INDUSTRIAL')
    RETURNING id INTO v_cat_industrial_id;

    INSERT INTO faixa_consumo (categoria_tarifaria_id, inicio, fim, valor_unitario) VALUES
        (v_cat_industrial_id,  0,    10, 1.00),
        (v_cat_industrial_id, 11,    20, 2.00),
        (v_cat_industrial_id, 21,    30, 3.00),
        (v_cat_industrial_id, 31, 99999, 4.00);

    -- ============================================================
    -- Categoria: PARTICULAR
    -- ============================================================
    INSERT INTO categoria_tarifaria (tabela_tarifaria_id, categoria)
    VALUES (v_tabela_id, 'PARTICULAR')
    RETURNING id INTO v_cat_particular_id;

    INSERT INTO faixa_consumo (categoria_tarifaria_id, inicio, fim, valor_unitario) VALUES
        (v_cat_particular_id,  0,    10, 1.50),
        (v_cat_particular_id, 11,    20, 2.25),
        (v_cat_particular_id, 21,    30, 3.00),
        (v_cat_particular_id, 31, 99999, 4.50);

    -- ============================================================
    -- Categoria: PÚBLICO
    -- ============================================================
    INSERT INTO categoria_tarifaria (tabela_tarifaria_id, categoria)
    VALUES (v_tabela_id, 'PUBLICO')
    RETURNING id INTO v_cat_publico_id;

    INSERT INTO faixa_consumo (categoria_tarifaria_id, inicio, fim, valor_unitario) VALUES
        (v_cat_publico_id,  0,    10, 0.80),
        (v_cat_publico_id, 11,    20, 1.20),
        (v_cat_publico_id, 21,    30, 1.80),
        (v_cat_publico_id, 31, 99999, 2.50);

    RAISE NOTICE 'Dados de exemplo inseridos com sucesso! Tabela ID: %', v_tabela_id;
END $$;

COMMIT;
