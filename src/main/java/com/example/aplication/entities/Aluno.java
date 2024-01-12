package com.example.aplication.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer idade;
    private LocalDate dataNascimento;
    private double notaPrimeiroSemestre;
    private double notaSegundoSemestre;
    private double mediaFinal;

    @ManyToOne
    private Turma turma;

    public Aluno(Turma turma, String nome, Integer idade, LocalDate dataNascimento, double notaPrimeiroSemestre, double notaSegundoSemestre) {
        this.turma = turma;
        this.nome = nome;
        this.idade = idade;
        this.dataNascimento = dataNascimento;
        this.notaPrimeiroSemestre = notaPrimeiroSemestre;
        this.notaSegundoSemestre = notaSegundoSemestre;
    }

    public Long getId() {
        return id;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getNotaPrimeiroSemestre() {
        return notaPrimeiroSemestre;
    }

    public void setNotaPrimeiroSemestre(double notaPrimeiroSemestre) {
        this.notaPrimeiroSemestre = notaPrimeiroSemestre;
    }

    public double getNotaSegundoSemestre() {
        return notaSegundoSemestre;
    }

    public void setNotaSegundoSemestre(double notaSegundoSemestre) {
        this.notaSegundoSemestre = notaSegundoSemestre;
        double media = (notaPrimeiroSemestre + notaSegundoSemestre) / 2;
        this.setMediaFinal(media);
    }

    public double getMediaFinal() {
        return mediaFinal;
    }

    public void setMediaFinal(double mediaFinal) {
        this.mediaFinal = mediaFinal;
    }
}
