package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.project.model.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long>{
	
}
