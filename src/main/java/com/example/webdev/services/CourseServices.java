package com.example.webdev.services;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev.model.Course;
import com.example.webdev.model.User;
import com.example.webdev.repositories.CourseRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseServices {
	@Autowired
	CourseRepository courseRepository;

	@GetMapping("/api/course")
	public Iterable<Course> findAllCourses() {
		return courseRepository.findAll();
	}

	@GetMapping("/api/course/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int id, HttpServletResponse response) {
		
		Optional<Course> data = courseRepository.findCourseById(id);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
	
	}

	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@DeleteMapping("/api/course/{courseId}")
	public void deleteCourse(@PathVariable("courseId") int id) {
		courseRepository.deleteById(id);
	}

	@PutMapping("/api/course/{courseId}")
	public Course updateCourseName(@PathVariable("courseId") int courseId,
																@RequestBody Course newCourse) {
		Optional<Course> data = courseRepository.findCourseById(courseId);
		if(data.isPresent()) {
			Course course = data.get();
			course.setTitle(newCourse.getTitle());
			courseRepository.save(course);
			return course;
		}
		return null;
	}
}
