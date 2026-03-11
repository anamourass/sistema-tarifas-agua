DROP TABLE IF EXISTS faixa_consumo CASCADE;
DROP TABLE IF EXISTS categoria_tarifaria CASCADE;
DROP TABLE IF EXISTS tabela_tarifaria CASCADE;

CREATE TABLE tabela_tarifaria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(255),
    data_vigencia DATE NOT NULL
);

CREATE TABLE categoria_tarifaria (
    id SERIAL PRIMARY KEY,
    tabela_tarifaria_id INTEGER NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    FOREIGN KEY (tabela_tarifaria_id) REFERENCES tabela_tarifaria(id)
);

CREATE TABLE faixa_consumo (
    id SERIAL PRIMARY KEY,
    categoria_tarifaria_id INTEGER NOT NULL,
    consumo_minimo INTEGER NOT NULL,
    consumo_maximo INTEGER NOT NULL,
    valor_tarifa NUMERIC(10,2) NOT NULL,
    FOREIGN KEY (categoria_tarifaria_id) REFERENCES categoria_tarifaria(id)
);