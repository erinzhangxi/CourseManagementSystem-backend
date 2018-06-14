package com.example.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev.model.FillInTheBlanksExamQuestion;

public interface FillInTheBlankQuestionRepositoryJoined extends CrudRepository<FillInTheBlanksExamQuestion, Integer>{}