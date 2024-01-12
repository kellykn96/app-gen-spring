package com.example.aplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aplication.entities.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{
    List<Aluno> findByNomeContaining(String nome);
}
