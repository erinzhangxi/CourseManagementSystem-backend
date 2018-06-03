package com.example.webdev.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdev.model.Lesson;
import com.example.webdev.model.Module;


public interface LessonRepository extends CrudRepository<Lesson, Integer>{

	@Query("SELECT l FROM Lesson l WHERE LOWER(l.id) = LOWER(:id)")
	Optional<Lesson> findLessonById(@Param("id") int id);
}
