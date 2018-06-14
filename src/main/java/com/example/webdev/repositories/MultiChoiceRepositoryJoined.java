package com.example.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev.model.MultipleChoiceQuestion;

public interface MultiChoiceRepositoryJoined extends CrudRepository<MultipleChoiceQuestion, Integer>{}