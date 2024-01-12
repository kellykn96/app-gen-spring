package com.example.aplication.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aplication.entities.Feedback;
import com.example.aplication.repositories.FeedbackRepository;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> listAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id).orElse(null);
    }

    public Feedback updateFeedback(Long id, Feedback novoFeedback) {
        Feedback feedbackExistente = feedbackRepository.findById(id).orElse(null);

        if (feedbackExistente != null) {
            feedbackExistente.setObservacoes(novoFeedback.getObservacoes());
            return feedbackRepository.save(feedbackExistente);
        }

        return null;
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }
}
