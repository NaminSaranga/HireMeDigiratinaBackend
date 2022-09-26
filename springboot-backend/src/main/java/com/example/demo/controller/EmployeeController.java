package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.EmployeeDigiratina;
import com.example.demo.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	// get all digiratina employees
	
	@GetMapping("/employees")
	public List<EmployeeDigiratina> getAllEmployees(){
		return employeeRepository.findAll();
	}	
	
	// create employee
	
	@PostMapping("/employees")
	public EmployeeDigiratina createEmployee(@RequestBody EmployeeDigiratina employeeDigiratina) {
		return employeeRepository.save(employeeDigiratina);
	}

}
