package com.example.webdev.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdev.model.Exam;

public interface ExamRepository	extends CrudRepository<Exam, Integer>{
	@Query("SELECT e FROM Exam e WHERE LOWER(e.id) = LOWER(:id)")
	Optional<Exam> findExamById(@Param("id") int id);	
}