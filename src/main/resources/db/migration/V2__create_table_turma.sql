CREATE TABLE turma (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    professor_id INT REFERENCES professor(id),
    ativo BOOLEAN
);