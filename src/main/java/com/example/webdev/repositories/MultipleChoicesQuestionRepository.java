package com.example.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev.model.MultipleChoiceQuestion;

public interface MultipleChoicesQuestionRepository
extends CrudRepository<MultipleChoiceQuestion, Integer> {

}