package com.example.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev.model.Exam;

public interface ExamRepository
extends CrudRepository<Exam, Integer>{

}