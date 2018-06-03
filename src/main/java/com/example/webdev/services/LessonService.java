package com.example.webdev.services;

import java.util.Date;
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
import com.example.webdev.model.Lesson;
import com.example.webdev.model.Module;
import com.example.webdev.repositories.CourseRepository;
import com.example.webdev.repositories.LessonRepository;
import com.example.webdev.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	@Autowired
	CourseRepository courseRepository;

	@Autowired
	ModuleRepository moduleRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public Lesson createLesson(
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@RequestBody Lesson newLesson) {
		Optional<Course> data = courseRepository.findCourseById(courseId);
		Optional<Module> mod = moduleRepository.findModuleById(moduleId);
		
		if(data.isPresent() && mod.isPresent()) {
			Module moddata = mod.get();
			
			newLesson.setModule(moddata);
			return lessonRepository.save(newLesson);
		}
		return null;		
	}
	
	@DeleteMapping("/api/course/{courseId}/module/{moduleId}/lesson/{lessonId}")
	public void deleteLesson(
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId,
			@PathVariable("lessonId") int lessonId)
	{
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Optional<Course> dat = courseRepository.findById(courseId);
			if (dat.isPresent()) {
				Course course = dat.get();
				Date date = new Date(System.currentTimeMillis());
				course.setModified(date);
			}
			lessonRepository.deleteById(lessonId);
		}
	}
	
	
	@GetMapping("/api/course/{courseId}/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonsForModule(
			@PathVariable("courseId") int courseId,
			@PathVariable("moduleId") int moduleId) {
		Optional<Module> data = moduleRepository.findModuleById(moduleId);
		if(data.isPresent()) {
			Module mod = data.get();
			return mod.getLessons();
		}
		return null;		
	}
	
	
	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons()
	{
		return (List<Lesson>) lessonRepository.findAll();
	}
}
