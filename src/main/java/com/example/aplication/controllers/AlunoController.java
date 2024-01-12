package com.example.aplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.aplication.entities.Aluno;
import com.example.aplication.services.AlunoService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "Aluno", description = "Operações relacionadas a aluno")
@RequestMapping("/api/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/nome")
    public List<Aluno> getAlunosByNome(@RequestParam String nome) {
        return alunoService.getAlunosByNome(nome);
    }

    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Aluno newAluno = alunoService.createAluno(aluno);
        return new ResponseEntity<>(newAluno, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listAlunoes() {
        List<Aluno> aluno = alunoService.listAllAlunos();
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {
        Aluno aluno = alunoService.getAlunoById(id);
        return aluno != null ?
               new ResponseEntity<>(aluno, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizarAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        Aluno alunoAtualizado = alunoService.updateAluno(id, aluno);
        return alunoAtualizado != null ?
               new ResponseEntity<>(alunoAtualizado, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAluno(@PathVariable Long id) {
        alunoService.deleteAluno(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
