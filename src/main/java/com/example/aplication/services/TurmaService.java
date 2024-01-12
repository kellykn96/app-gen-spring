package com.example.aplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aplication.entities.Professor;
import com.example.aplication.entities.Turma;
import com.example.aplication.repositories.TurmaRepository;

@Service
public class TurmaService {
    private final TurmaRepository turmaRepository;
    private final ProfessorService professorService; 

    @Autowired
    public TurmaService(TurmaRepository turmaRepository, ProfessorService professorService) {
        this.turmaRepository = turmaRepository;
        this.professorService = professorService;
    }

    public Turma createTurma(Turma turma) {
        try {
            if (turma.getProfessor() != null && turma.getProfessor().getId() != null) {
                Professor professor = professorService.getProfessorById(turma.getProfessor().getId());
                if (professor != null) { turma.setProfessor(professor);
                } else { throw new RuntimeException("Professor não encontrado com o ID fornecido"); }
            }

            return turmaRepository.save(turma);
        } catch (Exception e) {
            throw new RuntimeException("Professor não encontrado com o ID fornecido"); 
        }
    }

    public List<Turma> listAllTurmas() {
        return turmaRepository.findAll();
    }

    public Turma getTurmaById(Long id) {
        return turmaRepository.findById(id).orElse(null);
    }

    public Turma updateTurma(Long id, Turma newTurma) {
         try {
            if (newTurma.getProfessor() != null && newTurma.getProfessor().getId() != null) {
                Professor professor = professorService.getProfessorById(newTurma.getProfessor().getId());
                if (professor != null) { newTurma.setProfessor(professor);
                } else { throw new RuntimeException("Professor não encontrado com o ID fornecido"); }
            }

            Turma turmaExistente = turmaRepository.findById(id).orElse(null);

            if (turmaExistente != null) {
                turmaExistente.setDescricao(newTurma.getDescricao());
                turmaExistente.setAtivo(newTurma.isAtivo());
                turmaExistente.setProfessor(newTurma.getProfessor());
                return turmaRepository.save(turmaExistente);
            }

            return null;
        } catch (Exception e) {
            throw new RuntimeException("Professor não encontrado com o ID fornecido"); 
        }
    }

    public void deleteTurma(Long id) {
        turmaRepository.deleteById(id);
    }
}
