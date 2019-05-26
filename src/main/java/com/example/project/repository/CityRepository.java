package com.example.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{
	Optional<City> findByCityId(Long cityId);
	
	Optional<City> findByCityName(String cityName);
}
