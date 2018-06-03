package com.example.webdev.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.webdev.model.Module;
import com.example.webdev.model.User;

public interface ModuleRepository extends CrudRepository<Module, Integer>{
	@Query("SELECT m FROM Module m WHERE LOWER(m.id) = LOWER(:id)")
	Optional<Module> findModuleById(@Param("id") int id);	
	
}
