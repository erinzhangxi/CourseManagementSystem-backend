package com.example.webdev.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdev.model.Assignment;


public interface AssignmentRepository
extends CrudRepository<Assignment, Integer>{
	@Query("SELECT a FROM Assignment a WHERE LOWER(a.id) = LOWER(:id)")
	Optional<Assignment> findAssignmentById(@Param("id") int id);	

}