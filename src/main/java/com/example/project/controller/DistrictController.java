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

import com.example.project.model.District;
import com.example.project.service.DistrictService;

@RestController
@RequestMapping(path="/district")
public class DistrictController {
	@Autowired
	private DistrictService districtService;
	
	@GetMapping(path="/{id}")
	public ResponseEntity<?> getUnique(@PathVariable Long id){
		return districtService.getUnique(id);
	}
	
	@GetMapping(path="/all")
	public ResponseEntity<?> getAll(){
		return districtService.getAll();
	}
	
	@PostMapping(path="/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addCity(@RequestBody District district){
		return districtService.add(district);
	}
	
	@PutMapping(path="/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateCity(@RequestBody District district){
		return districtService.update(district);
	}
	
	@DeleteMapping(path="/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteCity(@PathVariable Long id){
		return districtService.delete(id);
	}
}
