package com.example.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.City;
import com.example.project.service.CityService;

@RestController
@RequestMapping("/city")
public class CityController {
	@Autowired
	private CityService cityService;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<?> getUnique(@PathVariable Long id){
		return cityService.getUnique(id);
	}
	
	@GetMapping(path="/all")
	public ResponseEntity<?> getAll(){
		return cityService.getAll();
	}
	
	@PostMapping(path="/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addCity(@RequestBody City city){
		return cityService.add(city);
	}
	
	@PutMapping(path="/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateCity(@RequestBody City city){
		return cityService.update(city);
	}
	
	@DeleteMapping(path="/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteCity(@PathVariable Long id){
		return cityService.delete(id);
	}
}
