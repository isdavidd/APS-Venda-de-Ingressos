-- Garantir que o banco exista
CREATE DATABASE IF NOT EXISTS db_aps2;
USE db_aps2;

-- Garantir que as tabelas existam
CREATE TABLE IF NOT EXISTS evento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    data DATETIME NOT NULL,
    local VARCHAR(255) NOT NULL,
    valor_ingresso DECIMAL(10, 2) NOT NULL,
    capacidade INT NOT NULL,
    descricao TEXT,
    uf CHAR(2) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    telefone VARCHAR(20),
    cpf VARCHAR(14) UNIQUE NOT NULL,
	senha VARCHAR(255) NOT NULL,
	role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS ingresso (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_evento BIGINT NOT NULL,
    id_usuario BIGINT,
    data_compra DATETIME,
	preco DECIMAL(10, 2) NOT NULL,
	status VARCHAR(15) DEFAULT 'Disponivel',
	nome_comprador VARCHAR(255),
	tipo_ingresso VARCHAR(10) DEFAULT 'Inteira',
	creation_time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_evento) REFERENCES evento(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS pagamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    metodo_pagamento VARCHAR(255) NOT NULL,
    email_enviado BOOLEAN DEFAULT FALSE,
    data_pagamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

DELIMITER $$

CREATE PROCEDURE ciar_indices()
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = 'db_aps2'
        AND table_name = 'evento'
        AND index_name = 'idx_nome'
    ) THEN
        CREATE INDEX idx_nome ON evento (nome);
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = 'db_aps2'
        AND table_name = 'evento'
        AND index_name = 'idx_uf'
    ) THEN
        CREATE INDEX idx_uf ON evento (uf);
    END IF;

    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = 'db_aps2'
        AND table_name = 'evento'
        AND index_name = 'idx_nome_uf'
    ) THEN
        CREATE INDEX idx_nome_uf ON evento (nome, uf);
    END IF;
END$$

DELIMITER ;

CALL ciar_indices();