package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.model.Contract;
import com.example.project.repository.ContractRepository;

@Service
public class ContractService {
	@Autowired
	private ContractRepository contractRepository;

	public ResponseEntity<?> getAll() {
		try {
			List<Contract> contracts = contractRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(contracts);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> add(Contract contract) {
		try {
			contractRepository.save(contract);
			return ResponseEntity.status(HttpStatus.OK).body("added!");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> update(Contract contract) {
		try {
			Contract existingContract = contractRepository.findById(contract.getId()).orElse(null);
			
			if(existingContract == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			else {
				existingContract.update(contract);
				contractRepository.save(existingContract);
				return ResponseEntity.status(HttpStatus.OK).body("updated!");
			}
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> delete(Long id) {
		try {
			Contract existingContract = contractRepository.findById(id).orElse(null);
			
			if(existingContract == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			else {
				contractRepository.delete(existingContract);
				return ResponseEntity.status(HttpStatus.OK).body("deleted!");
			}
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}
}
