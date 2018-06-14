package com.example.webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev.model.Assignment;
import com.example.webdev.model.Lesson;
import com.example.webdev.repositories.AssignmentRepository;
import com.example.webdev.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*")
public class AssignmentService {
	@Autowired
	AssignmentRepository repository;
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/assignment")
	public List<Assignment> findAllAssignments() {
		return (List<Assignment>) repository.findAll();	
	}
	
	@GetMapping("/api/assignment/{assignmentId}")
	public Assignment findAssignmentById(@PathVariable("assignmentId") int assignmentId) {
		Optional<Assignment> a = repository.findAssignmentById(assignmentId);
		if(a.isPresent()) {
			Assignment assignment = a.get();
			return assignment;
		}
		return null;
	}

	
	@GetMapping("/api/lesson/{lessonId}/assignment")
	public List<Assignment> findAllAssignmentsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if(optionalLesson.isPresent()) {
			Lesson lesson = optionalLesson.get();
			return lesson.getAssignments();
		}
		return null;
	}
	
	
	@PostMapping("/api/lesson/{lessonId}/assignment")
	public Assignment createAssignment(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Assignment newAssignment) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lsn = data.get();
			newAssignment.setLesson(lsn);
			return repository.save(newAssignment);
		}
		return null;
	}
	
	@DeleteMapping("/api/assignment/{assignmentId}")
	public void deleteAssignment(@PathVariable("assignmentId") int id) {
		repository.deleteById(id);
	}
	
	
}
