package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<?> getInfoByUsername(String username) {
		try {
			User existingUser = userRepository.findByUsername(username).orElse(null);

			if (existingUser == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			return ResponseEntity.status(HttpStatus.OK).body(existingUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}
	
	public ResponseEntity<?> getInfoById(Long id) {
		try {
			User existingUser = userRepository.findById(id).orElse(null);

			if (existingUser == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			return ResponseEntity.status(HttpStatus.OK).body(existingUser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}
	

	public ResponseEntity<?> getAll() {
		try {
			List<User> users = userRepository.findAll();
			
			return ResponseEntity.status(HttpStatus.OK).body(users);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}
	
}
