INSERT INTO endereco (bairro, cep, cidade, complemento, numero, rua, uf)
VALUES
    ('Centro', '20000-000', 'Rio de Janeiro', 'Apto 101', '123', 'Rua Principal', 'RJ');

INSERT INTO endereco (bairro, cep, cidade, complemento, numero, rua, uf)
VALUES
    ('Jardins', '01000-000', 'São Paulo', 'Bloco B', '456', 'Avenida Paulista', 'SP');

INSERT INTO endereco (bairro, cep, cidade, complemento, numero, rua, uf)
VALUES
    ('Savassi', '30100-000', 'Belo Horizonte', 'Cobertura', '789', 'Rua da Liberdade', 'MG');


INSERT INTO contato (ddd, email, telefone)
VALUES
    ('21', 'joao.silva@email.com', '987654321');

INSERT INTO contato (ddd, email, telefone)
VALUES
    ('11', 'maria.oliveira@email.com', '912345678');

INSERT INTO contato (ddd, email, telefone)
VALUES
    ('31', 'pedro.souza@email.com', '998765432');


INSERT INTO usuario (cpf, data_nascimento, nome, role, senha, id_contato, id_endereco)
VALUES
    ('12345678901', '1990-05-15', 'João Silva', 'ADMIN', 'senhaSegura123', 1, 1);

INSERT INTO usuario (cpf, data_nascimento, nome, role, senha, id_contato, id_endereco)
VALUES
    ('98765432109', '1985-10-22', 'Maria Oliveira', 'USER', 'senhaForte456', 2, 2);

INSERT INTO usuario (cpf, data_nascimento, nome, role, senha, id_contato, id_endereco)
VALUES
    ('45612378900', '2000-03-10', 'Pedro Souza', 'USER', 'senha123!@#', 3, 3);