package com.example.aplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aplication.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long>{
    
}
