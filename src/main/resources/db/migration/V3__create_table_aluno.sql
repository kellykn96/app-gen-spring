CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255),
    idade INT,
    turma_id INT REFERENCES turma(id),
    data_nascimento DATE,
    nota_primeiro_semestre DECIMAL(5, 2),
    nota_segundo_semestre DECIMAL(5, 2),
    media_final DECIMAL(5, 2)
);
