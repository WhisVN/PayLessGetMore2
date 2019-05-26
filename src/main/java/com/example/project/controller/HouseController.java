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

import com.example.project.model.House;
import com.example.project.service.HouseService;

@RestController
@RequestMapping(path="/house")
public class HouseController {
	@Autowired
	private HouseService houseService;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<?> getUnique(@PathVariable Long id){
		return houseService.getUnique(id);
	}
	
	@GetMapping(path="/all")
	public ResponseEntity<?> getAll(){
		return houseService.getAll();
	}
	
	@PostMapping(path="/add")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<?> addCity(@RequestBody House house){
		return houseService.add(house);
	}
	
	@PutMapping(path="/update")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public ResponseEntity<?> updateCity(@RequestBody House house){
		return houseService.update(house);
	}
	
	@DeleteMapping(path="/delete/{id}")
	@PreAuthorize("hasRole('USER')  or hasRole('ADMIN')")
	public ResponseEntity<?> deleteCity(@PathVariable Long id){
		return houseService.delete(id);
	}
}
