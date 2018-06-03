package com.example.webdev.services;

import java.util.Collections;
import java.util.List;
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

import com.example.webdev.model.Topic;
import com.example.webdev.model.Widget;
import com.example.webdev.repositories.TopicRepository;
import com.example.webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {
	
	@Autowired
	WidgetRepository widgetRepository;
	@Autowired
	private TopicRepository topicRepository;
	
	@GetMapping("/api/widget")
	public Iterable<Widget> findAllWidgets() {
		return widgetRepository.findAll();
	}
	
	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if(data.isPresent()) {
			Widget widget = data.get();
			return widget;
		}
		return null;
	}
	
	@GetMapping("api/topic/{topicId}/widget")
	public List<Widget> getWidgetByTopicId(@PathVariable("topicId") int topicId){
		Optional<Topic> data = topicRepository.findById(topicId);
		if(data.isPresent()) {
			Topic topic = data.get();
			List<Widget> widgets= topic.getWidgets();
			return widgets;
		}
		return null;	
	}


	@PostMapping("/api/widget")
	public List<Widget> saveWidgets(@RequestBody List<Widget> widgets) {
		widgetRepository.deleteAll();
		return (List<Widget>) widgetRepository.saveAll(widgets);
		
	}

	@PostMapping("/api/topic/{topicId}/widget")
	public List<Widget> saveWidgetsForTopic(@RequestBody List<Widget> widgets, @PathVariable("topicId") int topicId) {
		Optional<Topic> data = topicRepository.findById(topicId);
		if (data.isPresent()) {
			Topic topic = data.get();
			List<Widget> oldWidgets = topic.getWidgets();
			for (Widget w : oldWidgets) {
				widgetRepository.deleteById(w.getId());
			}
			for (Widget w : widgets) {
				Optional<Topic> datatopic=topicRepository.findById(topicId);
				if(data.isPresent()) {
					Topic t =datatopic.get();
					w.setTopic(t);
					widgetRepository.save(w);
				}
			}
			return (List<Widget>) widgetRepository.saveAll(widgets);
		}
		return null;
	
	}
	
	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget newWidget) {
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if (data.isPresent()) {
			widgetRepository.deleteById(widgetId);
			return widgetRepository.save(newWidget);
		}
		return null;
	}
	
	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId)
	{
		Optional<Widget> data = widgetRepository.findById(widgetId);
		if (data.isPresent()) {
			widgetRepository.deleteById(widgetId);
		}
	}
	
}
