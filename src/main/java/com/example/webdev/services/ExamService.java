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

import com.example.webdev.model.Course;
import com.example.webdev.model.Exam;
import com.example.webdev.model.Lesson;
import com.example.webdev.model.Question;
import com.example.webdev.repositories.ExamRepository;
import com.example.webdev.repositories.LessonRepository;

@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	@Autowired
	ExamRepository repository;
	@Autowired
	LessonRepository lessonRepository;
	
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) repository.findAll();	
	}
	
	@GetMapping("/api/exam/{examId}")
	public Exam findExamById(@PathVariable("examId") int examId) {
		Optional<Exam> data = repository.findExamById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			return exam;
		}
		return null;
	}

	@GetMapping("/api/lesson/{lessonId}/exam")
	public List<Exam> findAllExamsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if(optionalLesson.isPresent()) {
			Lesson lesson = optionalLesson.get();
			return lesson.getExams();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lessonId}/exam/{examId}/questions")
	public List<Question> findAllQuestionsForExam(@PathVariable("lessonId") int lessonId, 
												@PathVariable("examId") int examId) {
		Optional<Exam> data = repository.findById(examId);
		if(data.isPresent()) {
			Exam e = data.get();
			return e.getQuestions();
		}
		return null;		
	}
	
	
	@PostMapping("/api/lesson/{lessonId}/exam")
	public Exam createExam(
			@PathVariable("lessonId") int lessonId,
			@RequestBody Exam newExam) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lsn = data.get();
			newExam.setLesson(lsn);
			return repository.save(newExam);
		}
		return null;
	}
	
	@DeleteMapping("/api/exam/{examId}")
	public void deleteExam(@PathVariable("examId") int id) {
		repository.deleteById(id);
	}
}
