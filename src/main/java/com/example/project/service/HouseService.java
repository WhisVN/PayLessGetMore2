package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.model.District;
import com.example.project.model.House;
import com.example.project.model.User;
import com.example.project.repository.DistrictRepository;
import com.example.project.repository.HouseRepository;
import com.example.project.repository.UserRepository;

@Service
public class HouseService {
	@Autowired
	private HouseRepository houseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DistrictRepository districtRepository;

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
			
			User owner = userRepository.findById(house.getOwner().getId()).orElse(null);
			District district = districtRepository.findById(house.getDistrict().getDistrictId()).orElse(null);
			house.setDistrict(district);
			house.setOwner(owner);
					
			existingHouse.update(house);
			houseRepository.save(existingHouse);
			
			return ResponseEntity.status(HttpStatus.OK).body("updated!");
		} catch (Exception e) {
			//e.printStackTrace();
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
