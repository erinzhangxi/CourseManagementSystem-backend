package com.example.webdev.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="Faculty")
public class Faculty extends User {

	public Faculty(String username, String password) {
		super(username, password);
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String tenured;
	private String office;
	
	public String getTenured() {
		return tenured;
	}
	public void setTenured(String tenured) {
		this.tenured = tenured;
	}
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	
}
