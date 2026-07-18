-- Criando a tabela de Passageiro primeiro, pois a tabela de Viagem depende dela!
CREATE TABLE passageiro (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            nome VARCHAR(255) NOT NULL,
                            idade BIGINT,
                            cpf VARCHAR(255) NOT NULL,
                            email VARCHAR(255) NOT NULL,
                            categoria_fidelidade VARCHAR(255),
                            milhas_acumuladas INT
);

-- Criando a tabela de Viagem com o relacionamento de Chave Estrangeira (Foreign Key)
CREATE TABLE viagem (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        origem VARCHAR(255),
                        destino VARCHAR(255),
                        data_voo VARCHAR(255),
                        preco_original DOUBLE PRECISION NOT NULL,
                        preco_final DOUBLE PRECISION NOT NULL,
                        passageiro_id BIGINT,
                        CONSTRAINT fk_viagem_passageiro FOREIGN KEY (passageiro_id) REFERENCES passageiro(id)
);