drop database wallet_db;

CREATE DATABASE IF NOT EXISTS wallet_db CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
USE wallet_db;

-- Tabela Perfil
CREATE TABLE Perfil (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(100) NOT NULL
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Endereco
CREATE TABLE Endereco (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          logradouro VARCHAR(100),
                          numero VARCHAR(20),
                          complemento VARCHAR(50),
                          bairro VARCHAR(50),
                          cidade VARCHAR(50),
                          estado VARCHAR(2),
                          cep VARCHAR(10)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Contato
CREATE TABLE Contato (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         telefone VARCHAR(20),
                         email VARCHAR(100)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Usuario
CREATE TABLE Usuario (
                         numeroDocumento BIGINT PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         senha VARCHAR(100) NOT NULL,
                         dataNascimento DATE,
                         idEndereco BIGINT,
                         idContato BIGINT,
                         idPerfil BIGINT,
                         FOREIGN KEY (idEndereco) REFERENCES Endereco(id),
                         FOREIGN KEY (idContato) REFERENCES Contato(id),
                         FOREIGN KEY (idPerfil) REFERENCES Perfil(id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Conta
CREATE TABLE Conta (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       numeroConta BIGINT,
                       saldo DECIMAL(15, 2),
                       dataCriacao TIMESTAMP,
                       idUsuario BIGINT,
                       FOREIGN KEY (idUsuario) REFERENCES Usuario(numeroDocumento)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Pix
CREATE TABLE Pix (
                     chavePix VARCHAR(255) PRIMARY KEY,
                     idConta BIGINT,
                     FOREIGN KEY (idConta) REFERENCES Conta(id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela TipoTransacao
CREATE TABLE TipoTransacao (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               descricao VARCHAR(50) NOT NULL
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Transacao
CREATE TABLE Transacao (
                           id VARCHAR(255) PRIMARY KEY,
                           dataTransacao TIMESTAMP,
                           idTipo BIGINT,
                           valor DECIMAL(15, 2),
                           idContaOrigem BIGINT,
                           idContaDestino BIGINT,
                           FOREIGN KEY (idContaOrigem) REFERENCES Conta(id),
                           FOREIGN KEY (idContaDestino) REFERENCES Conta(id),
                           FOREIGN KEY (idTipo) REFERENCES TipoTransacao(id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



-- Tabela Boleto
CREATE TABLE Boleto (
                        id VARCHAR(255) PRIMARY KEY,
                        valor DECIMAL(15, 2) NOT NULL,
                        dataVencimento DATE NOT NULL,
                        statusBoleto VARCHAR(20) NOT NULL,
                        dataGeracao TIMESTAMP,
                        idTransacao VARCHAR(255),
                        FOREIGN KEY (idTransacao) REFERENCES Transacao(id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela Permissao
CREATE TABLE Permissao (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           nome VARCHAR(100) NOT NULL
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tabela PermissaoPerfil (associação de permissões e perfis)
CREATE TABLE PermissaoPerfil (
                                 perfil_id BIGINT,
                                 permissao_id BIGINT,
                                 PRIMARY KEY (perfil_id, permissao_id),
                                 FOREIGN KEY (perfil_id) REFERENCES Perfil(id),
                                 FOREIGN KEY (permissao_id) REFERENCES Permissao(id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE Extrato (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         dataExtrato TIMESTAMP NOT NULL,
                         idConta BIGINT NOT NULL,
                         descricao TEXT,
                         FOREIGN KEY (idConta) REFERENCES Conta(id)
)CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;