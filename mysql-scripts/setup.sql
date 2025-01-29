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
    uf CHAR(2) NOT NULL,
    endereco VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE,
    telefone VARCHAR(20),
    cpf VARCHAR(14) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS ingresso (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_evento BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    data_compra DATETIME NOT NULL,
	preco DOUBLE NOT NULL,
	status VARCHAR(50) DEFAULT 'Disponível',
	nome_comprador VARCHAR(255),
	creation_time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_evento) REFERENCES evento(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

DELIMITER $$

CREATE PROCEDURE ciar_indices()
BEGIN
    -- Verifica se o índice idx_nome existe e cria, caso não exista
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = 'db_aps2'
        AND table_name = 'evento'
        AND index_name = 'idx_nome'
    ) THEN
        CREATE INDEX idx_nome ON evento (nome);
    END IF;

    -- Verifica se o índice idx_uf existe e cria, caso não exista
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.statistics
        WHERE table_schema = 'db_aps2'
        AND table_name = 'evento'
        AND index_name = 'idx_uf'
    ) THEN
        CREATE INDEX idx_uf ON evento (uf);
    END IF;

    -- Verifica se o índice idx_nome_uf existe e cria, caso não exista
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

-- Chama o procedimento para executar as verificações e criação dos índices
CALL ciar_indices();