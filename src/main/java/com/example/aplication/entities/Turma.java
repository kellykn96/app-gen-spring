package com.example.aplication.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    @ManyToOne
    private Professor professor;

    private boolean ativo;

    public Turma(Integer id) {
        this.id = (long) id;
    }
    
    public Turma(String descricao, Professor professor, boolean ativo) {
        this.descricao = descricao;
        this.professor = professor;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
