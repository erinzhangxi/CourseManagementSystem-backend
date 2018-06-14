package com.example.webdev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class FillInTheBlanksExamQuestion extends Question {
	@Column(name = "VARIABLES", nullable = false)

	private String variables;

	public String getVariables() {
		return variables;
	}

	public void setVariables(String variables) {
		this.variables = variables;
	}	
}
