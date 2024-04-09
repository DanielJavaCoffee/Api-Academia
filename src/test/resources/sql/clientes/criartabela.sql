DROP TABLE IF EXISTS CLIENTES;
CREATE TABLE CLIENTES (
    id CHAR(36) CHARACTER SET latin1 COLLATE latin1_general_cs PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    observacao TEXT,
    data_de_nascimento DATE NOT NULL,
    is_ativo BOOLEAN NOT NULL,
    peso DECIMAL(5,2),
    altura DECIMAL(5,2),
    role VARCHAR(50) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    criado_por VARCHAR(255),
    modificado_por VARCHAR(255),
    endereco_id CHAR(36) CHARACTER SET latin1 COLLATE latin1_general_cs
);

DROP TABLE IF EXISTS ENDERECOS;
CREATE TABLE ENDERECOS (
    id CHAR(36) CHARACTER SET latin1 COLLATE latin1_general_cs PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL
);
