package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.model.House;
import com.example.project.repository.HouseRepository;

@Service
public class HouseService {
	@Autowired
	private HouseRepository houseRepository;

	public ResponseEntity<?> getUnique(Long id) {
		try {
			House existingHouse = houseRepository.findById(id).orElse(null);

			if (existingHouse == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			return ResponseEntity.status(HttpStatus.OK).body(existingHouse);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> getAll() {
		try {
			List<House> houses = houseRepository.findAll();

			return ResponseEntity.status(HttpStatus.OK).body(houses);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> add(House house) {
		try {
			houseRepository.save(house);
			return ResponseEntity.status(HttpStatus.OK).body("added!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> update(House house) {
		try {
			House existingHouse = houseRepository.findById(house.getHouseId()).orElse(null);
			
			if (existingHouse == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			
			existingHouse.update(house);
			houseRepository.save(existingHouse);
			
			return ResponseEntity.status(HttpStatus.OK).body("updated!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> delete(Long id) {
		try {
			House existingHouse = houseRepository.findById(id).orElse(null);

			if (existingHouse == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			else {
				houseRepository.delete(existingHouse);
				return ResponseEntity.status(HttpStatus.OK).body("deleted!");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

}
