package com.example.webdev.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev.model.Lesson;
import com.example.webdev.model.Widget;
import com.example.webdev.repositories.LessonRepository;
import com.example.webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {
	@Autowired
	WidgetRepository repository;
	@Autowired
	LessonRepository lessonRepository;

	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(@PathVariable("lessonId") int lessonId) {
		Optional<Lesson> optionalLesson = lessonRepository.findById(lessonId);
		if(optionalLesson.isPresent()) {
			Lesson lesson = optionalLesson.get();
			return lesson.getWidgets();
		}
		return null;
	}

	@PostMapping("/api/widget/save")
	public void saveAllWidgets(@RequestBody
			List<Widget> widgets) {
		repository.deleteAll();
		for(Widget widget: widgets) {
			repository.save(widget);
		}
	}

	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) repository.findAll();
	}
	@PostMapping("/api/lesson/{lessonId}/widget")
	public Widget createWidgetForLesson(@RequestBody Widget widget, @PathVariable("lessonId") int lessonId) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lsn =data.get();
			widget.setLesson(lsn);
			repository.save(widget);
		}

	return null;
}

}
