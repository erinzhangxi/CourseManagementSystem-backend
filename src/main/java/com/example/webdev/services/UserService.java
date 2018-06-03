package com.example.webdev.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.webdev.model.User;
import com.example.webdev.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository repository;

	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int id) {
		repository.deleteById(id);
	}

	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}

	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			repository.save(user);
			return user;
		}
		return null;
	}


	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) repository.findAll();
	}

	@GetMapping("/api/user/id/{userId}")
	public User findUserById(@PathVariable("userId") int userId, HttpServletResponse response) {
		Optional<User> data = repository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}

	@PostMapping("api/user/{username}")
	public User findUserByUsername(@PathVariable("username") String username, HttpServletResponse response) {
		Optional<User> data = repository.findUserByUsername(username);
		if(data.isPresent()) {
			return data.get();
		}
		else {
			response.setStatus(HttpServletResponse.SC_CONFLICT);
			return null;
		}
	}

	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpServletResponse response) {
		
//		 this.repository.findUserByCredentials(user.getUsername(), user.getPassword())
//		 	.orElseThrow(
//		 			() -> new UserNotFoundException(user.getId()));
		 Optional<User> data = repository.findUserByCredentials(user.getUsername(), user.getPassword());
	        if(data.isPresent()) {
	            return data.get();
	        }
	        response.setStatus(HttpServletResponse.SC_CONFLICT);
	        return null;
		 
	}
	
	@GetMapping("/api/session/invalidate")
	public String invalidateSession(HttpSession session) {
		session.invalidate();
		return "session invalidated";
	}
	
	@GetMapping("/api/profile")
	public User profile(HttpSession session) {
		User currentUser = (User) session.getAttribute("currentUser");
		return currentUser;
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User newUser, HttpServletResponse response) {
		Optional<User> data = repository.findById(newUser.getId());
        if(data.isPresent()) {
            User user = data.get();
            user.setPhone(newUser.getPhone());
            user.setDateOfBirth(newUser.getDateOfBirth());
            user.setUsername(newUser.getUsername());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setRole(newUser.getRole());
            user.setEmail(newUser.getEmail());
            repository.save(user);
            return user;
        }
        
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        return null;
	}

	@PostMapping("/api/logout")
	public void logout(@RequestBody User user, HttpSession session) {
		session.invalidate();
	}

}
