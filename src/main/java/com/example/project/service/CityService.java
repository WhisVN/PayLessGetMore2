package com.example.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.project.model.City;
import com.example.project.repository.CityRepository;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;

	public ResponseEntity<?> getUnique(Long id) {
		try {
			City existingCity = cityRepository.findById(id).orElse(null);

			if (existingCity == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			return ResponseEntity.status(HttpStatus.OK).body(existingCity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> getAll() {
		try {
			List<City> cities = cityRepository.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(cities);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> add(City city) {
		try {
			City existingCity = cityRepository.findByCityName(city.getCityName()).orElse(null);

			if (existingCity != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already existed!");
			}
			
			cityRepository.save(city);
			return ResponseEntity.status(HttpStatus.OK).body("added!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> update(City city) {
		try {
			City existingCity = cityRepository.findByCityId(city.getCityId()).orElse(null);

			if (existingCity == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			
			existingCity.update(city);
			cityRepository.save(existingCity);
			return ResponseEntity.status(HttpStatus.OK).body("updated!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}

	public ResponseEntity<?> delete(Long id) {
		try {
			City existingCity = cityRepository.findByCityId(id).orElse(null);

			if (existingCity == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found!");
			}
			else {
				cityRepository.delete(existingCity);
				return ResponseEntity.status(HttpStatus.OK).body("deleted!");
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Exception: " + e.getMessage());
		}
	}
}
