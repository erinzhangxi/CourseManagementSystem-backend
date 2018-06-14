package com.example.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev.model.TrueFalseQuestion;

public interface TrueOrFalseQuestionRepositoryJoined extends CrudRepository<TrueFalseQuestion, Integer>{}