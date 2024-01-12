package com.example.aplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aplication.entities.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
     List<Professor> findByNomeContaining(String nome);
}
