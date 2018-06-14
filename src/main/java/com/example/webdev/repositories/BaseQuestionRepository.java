package com.example.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev.model.Question;

public interface BaseQuestionRepository extends CrudRepository<Question, Integer>{}

