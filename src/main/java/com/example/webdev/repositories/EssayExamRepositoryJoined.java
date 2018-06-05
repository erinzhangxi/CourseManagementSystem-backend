package com.example.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev.model.EssayExamQuestion;

public interface EssayExamRepositoryJoined extends CrudRepository<EssayExamQuestion, Integer>{}
