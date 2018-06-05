package com.example.webdev.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MultipleChoiceQuestion extends Question {
	
	private int correctOption;
	@OneToMany(mappedBy="multipleChoice")
	@JsonIgnore
	private List<Choice> choices;
	
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}
	public List<Choice> getChoices() {
		return choices;
	}
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
	
	
}