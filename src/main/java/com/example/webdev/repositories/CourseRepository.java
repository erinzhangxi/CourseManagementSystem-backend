package com.example.webdev.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdev.model.Course;
import com.example.webdev.model.Module;


public interface CourseRepository
	extends CrudRepository<Course, Integer> {
	@Query("SELECT c FROM Course c WHERE LOWER(c.id) = LOWER(:id)")
	Optional<Course> findCourseById(@Param("id") int id);
}