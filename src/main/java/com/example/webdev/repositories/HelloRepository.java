package com.example.webdev.repositories;
import org.springframework.data.repository.CrudRepository;
import com.example.webdev.model.Hello;

public interface HelloRepository
	extends CrudRepository<Hello, Integer> {
}