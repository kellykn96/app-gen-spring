package com.example.aplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aplication.entities.Professor;
import com.example.aplication.repositories.ProfessorRepository;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> getProfessorByNome(String nome) {
        return professorRepository.findByNomeContaining(nome);
    }

    public Professor createProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    public List<Professor> listAllProfessores() {
        return professorRepository.findAll();
    }

    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id).orElse(null);
    }

    public Professor updateProfessor(Long id, Professor novoProfessor) {
        Professor professorExistente = professorRepository.findById(id).orElse(null);

        if (professorExistente != null) {
            professorExistente.setNome(novoProfessor.getNome());
            professorExistente.setIdade(novoProfessor.getIdade());
            professorExistente.setMateria(novoProfessor.getMateria());
            professorExistente.setObservacoes(novoProfessor.getObservacoes());
            return professorRepository.save(professorExistente);
        }

        return null;
    }

    public void deleteProfessor(Long id) {
        professorRepository.deleteById(id);
    }
}
