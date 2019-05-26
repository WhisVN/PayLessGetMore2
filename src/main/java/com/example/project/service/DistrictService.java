package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.model.District;
import com.example.project.repository.DistrictRepository;

@Service
public class DistrictService {
	@Autowired
	private DistrictRepository districtRepository;

	public ResponseEntity<?> getUnique(Long id) {
		try {
			District existingDistrict = districtRepository.findById(id).orElse(null);

			if (existingDistrict == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			return ResponseEntity.status(HttpStatus.OK).body(existingDistrict);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> getAll() {
		try {
			List<District> districts = districtRepository.findAll();

			return ResponseEntity.status(HttpStatus.OK).body(districts);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> update(District district) {
		try {
			District existingDistrict = districtRepository.findById(district.getDistrictId()).orElse(null);

			if (existingDistrict == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			
			existingDistrict.update(district);
			districtRepository.save(existingDistrict);
			
			return ResponseEntity.status(HttpStatus.OK).body("updated!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> add(District district) {
		try {
			District existingDistrict = districtRepository.findByDistrictName(district.getDistrictName()).orElse(null);

			if (existingDistrict != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already existed!");
			}
			
			districtRepository.save(district);
			return ResponseEntity.status(HttpStatus.OK).body("added!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> delete(Long id) {
		try {
			District existingDistrict = districtRepository.findById(id).orElse(null);

			if (existingDistrict == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			
			districtRepository.delete(existingDistrict);
			
			return ResponseEntity.status(HttpStatus.OK).body("deleted!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

}
