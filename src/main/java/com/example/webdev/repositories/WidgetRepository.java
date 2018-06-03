package com.example.webdev.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdev.model.Widget;

public interface WidgetRepository  extends CrudRepository<Widget, Integer>{

	@Query("SELECT w FROM Widget w WHERE LOWER(w.id) = LOWER(:id)")
	Optional<Widget> findWidgetById(@Param("id") int id);	

}

