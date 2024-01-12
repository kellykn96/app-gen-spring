package com.example.aplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aplication.entities.Turma;
import com.example.aplication.entities.Aluno;
import com.example.aplication.repositories.AlunoRepository;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final TurmaService turmaService; 

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, TurmaService turmaService) {
        this.alunoRepository = alunoRepository;
        this.turmaService = turmaService;
    }

    public List<Aluno> getAlunosByNome(String nome) {
        return alunoRepository.findByNomeContaining(nome);
    }

    public Aluno createAluno(Aluno aluno) {
        try {
            if (aluno.getTurma() != null && aluno.getTurma().getId() != null) {
                Turma turma = turmaService.getTurmaById(aluno.getTurma().getId());
                if (turma != null) { aluno.setTurma(turma);
                } else { throw new RuntimeException("Turma não encontrado com o ID fornecido"); }
            }

            return alunoRepository.save(aluno);
        } catch (Exception e) {
            throw new RuntimeException("Turma não encontrado com o ID fornecido"); 
        }
    }

    public List<Aluno> listAllAlunos() {
        return alunoRepository.findAll();
    }

    public Aluno getAlunoById(Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    public Aluno updateAluno(Long id, Aluno newAluno) {
         try {
            if (newAluno.getTurma() != null && newAluno.getTurma().getId() != null) {
                Turma turma = turmaService.getTurmaById(newAluno.getTurma().getId());
                if (turma != null) { newAluno.setTurma(turma);
                } else { throw new RuntimeException("Turma não encontrado com o ID fornecido"); }
            }

            Aluno alunoExistente = alunoRepository.findById(id).orElse(null);

            if (alunoExistente != null) {
                alunoExistente.setNome(newAluno.getNome());
                alunoExistente.setIdade(newAluno.getIdade());
                alunoExistente.setDataNascimento(newAluno.getDataNascimento());
                alunoExistente.setNotaPrimeiroSemestre(newAluno.getNotaPrimeiroSemestre());
                alunoExistente.setNotaSegundoSemestre(newAluno.getNotaSegundoSemestre());
                alunoExistente.setTurma(newAluno.getTurma());
                return alunoRepository.save(alunoExistente);
            }

            return null;
        } catch (Exception e) {
            throw new RuntimeException("Turma não encontrado com o ID fornecido"); 
        }
    }

    public void deleteAluno(Long id) {
        alunoRepository.deleteById(id);
    }
}
