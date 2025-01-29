-- Garantir que o banco exista
CREATE DATABASE IF NOT EXISTS eventos_db;
USE eventos_db;

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
    id_evento BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    data_compra DATETIME NOT NULL,
    status VARCHAR(50) DEFAULT 'válido',
    PRIMARY KEY (id_evento, id_usuario, data_compra),
    FOREIGN KEY (id_evento) REFERENCES evento(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

-- Garantir índices para otimizar as buscas
DO
$$
BEGIN
    IF NOT EXISTS (
        SELECT 1 FROM information_schema.statistics
        WHERE table_schema = 'eventos_db' AND table_name = 'evento' AND index_name = 'idx_nome'
    ) THEN
        CREATE INDEX idx_nome ON evento (nome);
    END IF;

    IF NOT EXISTS (
        SELECT 1 FROM information_schema.statistics
        WHERE table_schema = 'eventos_db' AND table_name = 'evento' AND index_name = 'idx_uf'
    ) THEN
        CREATE INDEX idx_uf ON evento (uf);
    END IF;

    IF NOT EXISTS (
        SELECT 1 FROM information_schema.statistics
        WHERE table_schema = 'eventos_db' AND table_name = 'evento' AND index_name = 'idx_nome_uf'
    ) THEN
        CREATE INDEX idx_nome_uf ON evento (nome, uf);
    END IF;
END$$;

-- Garantir privilégios ao usuário
GRANT ALL PRIVILEGES ON eventos_db.* TO 'usuario1'@'%';
