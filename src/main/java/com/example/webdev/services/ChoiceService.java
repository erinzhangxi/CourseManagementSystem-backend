package com.example.webdev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdev.model.Choice;
import com.example.webdev.repositories.ChoiceRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChoiceService {
	@Autowired
	ChoiceRepository choiceRepo;
	
	@GetMapping("/api/choices")
	public Iterable<Choice> findAllChoices() {
		return choiceRepo.findAll(); 
	}
	
	@GetMapping("/api/choice/{choiceId}")
	public Choice findChoiceById(@PathVariable("choiceId") int id) {
		Optional<Choice> data = choiceRepo.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			return null;
		}
	}

	@PostMapping("/api/choice")
	public Choice createChoice(@RequestBody Choice choice) {
		return choiceRepo.save(choice);
	}

	@DeleteMapping("/api/choice/{choiceId}")
	public void deleteChoice(@PathVariable("choiceId") int id) {
		choiceRepo.deleteById(id);
	}
}