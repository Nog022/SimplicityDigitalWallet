-- Criar o banco de dados
CREATE DATABASE IF NOT EXISTS simplicity_digital_db CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
USE simplicity_digital_db;

-- Tabela Endereco
CREATE TABLE Endereco (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(100),
    numero VARCHAR(20),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    uf VARCHAR(2),
    cep VARCHAR(10)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Contato
CREATE TABLE Contato (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    ddd VARCHAR(2),
    telefone VARCHAR(20),
    email VARCHAR(100)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Usuario
CREATE TABLE Usuario (
    cpf BIGINT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    senha VARCHAR(100) NOT NULL,
    dataNascimento DATE,
    idEndereco BIGINT,
    idContato BIGINT,
    isAdmin BOOLEAN,
    FOREIGN KEY (idEndereco) REFERENCES Endereco(id),
    FOREIGN KEY (idContato) REFERENCES Contato(id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Conta
CREATE TABLE Conta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numeroConta BIGINT,
    saldo DECIMAL(15, 2),
    dataCriacao TIMESTAMP,
    idUsuario BIGINT,
    FOREIGN KEY (idUsuario) REFERENCES Usuario(cpf)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Transacao
CREATE TABLE Transacao (
    id VARCHAR(255) PRIMARY KEY,
    dataTransacao TIMESTAMP,
    valor DECIMAL(15, 2),
    idContaOrigem BIGINT,
    idContaDestino BIGINT,
    FOREIGN KEY (idContaOrigem) REFERENCES Conta(id),
    FOREIGN KEY (idContaDestino) REFERENCES Conta(id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Saque
CREATE TABLE Saque (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor DECIMAL(15, 2),
    idConta BIGINT,
    FOREIGN KEY (idConta) REFERENCES Conta(id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tipo para Deposito
CREATE TYPE tipoDeposito AS ENUM ('PIX', 'Boleto');

-- Tabela Deposito
CREATE TABLE Deposito (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    valor DECIMAL(15, 2),
    idConta BIGINT,
    dataTransacao TIMESTAMP,
    tipo tipoDeposito NOT NULL,
    FOREIGN KEY (idConta) REFERENCES Conta(id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
