package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>{

}
