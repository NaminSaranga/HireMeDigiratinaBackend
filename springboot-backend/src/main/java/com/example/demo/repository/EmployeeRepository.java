package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EmployeeDigiratina;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDigiratina, Long>{

}
