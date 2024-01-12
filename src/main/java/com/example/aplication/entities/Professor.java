package com.example.aplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String  nome;
    private Integer idade;
    private String  materia;
    private String  observacoes;

    public Professor(Integer id) {
        this.id = (long) id;
    }

    public Professor(String nome, Integer idade, String materia, String observacoes) {
        this.nome = nome;
        this.idade = idade;
        this.materia = materia;
        this.observacoes = observacoes;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getMateria() {
        return materia;
    }

    public String getObservacoes() {
        return observacoes;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
