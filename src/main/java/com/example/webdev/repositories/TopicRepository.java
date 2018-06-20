package com.example.webdev.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev.model.Topic;


public interface TopicRepository extends CrudRepository<Topic, Integer> {

}