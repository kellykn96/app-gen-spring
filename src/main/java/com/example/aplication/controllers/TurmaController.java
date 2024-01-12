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
import org.springframework.web.bind.annotation.RestController;

import com.example.aplication.entities.Turma;
import com.example.aplication.services.TurmaService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "Turma", description = "Operações relacionadas a Turma")
@RequestMapping("/api/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity<Turma> criarTurma(@RequestBody Turma turma) {
        Turma newTurma = turmaService.createTurma(turma);
        return new ResponseEntity<>(newTurma, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Turma>> listTurmaes() {
        List<Turma> turma = turmaService.listAllTurmas();
        return new ResponseEntity<>(turma, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarTurmaPorId(@PathVariable Long id) {
        Turma turma = turmaService.getTurmaById(id);
        return turma != null ?
               new ResponseEntity<>(turma, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Long id, @RequestBody Turma turma) {
        Turma turmaAtualizado = turmaService.updateTurma(id, turma);
        return turmaAtualizado != null ?
               new ResponseEntity<>(turmaAtualizado, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTurma(@PathVariable Long id) {
        turmaService.deleteTurma(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
