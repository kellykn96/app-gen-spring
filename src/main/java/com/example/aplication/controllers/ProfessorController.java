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

import com.example.aplication.entities.Professor;
import com.example.aplication.services.ProfessorService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "Professor", description = "Operações relacionadas a professor")
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/nome")
    public List<Professor> getProfessorByNome(@RequestParam String nome) {
        return professorService.getProfessorByNome(nome);
    }

    @PostMapping
    public ResponseEntity<Professor> criarProfessor(@RequestBody Professor professor) {
        Professor newProfessor = professorService.createProfessor(professor);
        return new ResponseEntity<>(newProfessor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listProfessores() {
        List<Professor> professores = professorService.listAllProfessores();
        return new ResponseEntity<>(professores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarProfessorPorId(@PathVariable Long id) {
        Professor professor = professorService.getProfessorById(id);
        return professor != null ?
               new ResponseEntity<>(professor, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        Professor professorAtualizado = professorService.updateProfessor(id, professor);
        return professorAtualizado != null ?
               new ResponseEntity<>(professorAtualizado, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProfessor(@PathVariable Long id) {
        professorService.deleteProfessor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
