package com.example.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{
	Optional<District> findByDistrictId(Long districtId);
	
	Optional<District> findByDistrictName(String districtName);
}
