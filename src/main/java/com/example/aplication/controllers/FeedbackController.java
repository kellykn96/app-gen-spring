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

import com.example.aplication.entities.Feedback;
import com.example.aplication.services.FeedbackService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin
@Tag(name = "Feedback", description = "Operações relacionadas a feedback")
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService FeedbackService;

    @Autowired
    public FeedbackController(FeedbackService FeedbackService) {
        this.FeedbackService = FeedbackService;
    }

    @PostMapping
    public ResponseEntity<Feedback> criarFeedback(@RequestBody Feedback Feedback) {
        Feedback newFeedback = FeedbackService.createFeedback(Feedback);
        return new ResponseEntity<>(newFeedback, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> listFeedbackes() {
        List<Feedback> Feedbackes = FeedbackService.listAllFeedback();
        return new ResponseEntity<>(Feedbackes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> buscarFeedbackPorId(@PathVariable Long id) {
        Feedback Feedback = FeedbackService.getFeedbackById(id);
        return Feedback != null ?
               new ResponseEntity<>(Feedback, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> atualizarFeedback(@PathVariable Long id, @RequestBody Feedback Feedback) {
        Feedback FeedbackAtualizado = FeedbackService.updateFeedback(id, Feedback);
        return FeedbackAtualizado != null ?
               new ResponseEntity<>(FeedbackAtualizado, HttpStatus.OK) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFeedback(@PathVariable Long id) {
        FeedbackService.deleteFeedback(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
