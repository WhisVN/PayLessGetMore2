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

import com.example.project.model.Contract;
import com.example.project.service.ContractService;

@RestController
@RequestMapping(path="/contract")
public class ContractController {
	@Autowired
	private ContractService contractService;
	
	@GetMapping(path="/all")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getAll() {
		return contractService.getAll();
	}
	
	@PostMapping(path="/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> add(@RequestBody Contract contract){
		return contractService.add(contract);
	}
	
	@PutMapping(path="/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody Contract contract) {
		return contractService.update(contract);
	}
	
	@DeleteMapping(path="/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return contractService.delete(id);
	}
}
