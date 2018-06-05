package com.example.webdev.model;
import javax.persistence.Entity;

@Entity
public class EssayExamQuestion extends Question {
	private String answer;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}	
	
}

