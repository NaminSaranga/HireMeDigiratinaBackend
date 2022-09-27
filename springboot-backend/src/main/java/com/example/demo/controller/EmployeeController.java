package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
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
	
	// create digiratina employee
	
	@PostMapping("/employees")
	public EmployeeDigiratina createEmployee(@RequestBody EmployeeDigiratina employeeDigiratina) {
		return employeeRepository.save(employeeDigiratina);
	}
	
	// get digiratina employees by id
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeDigiratina> getEmployeeById(@PathVariable Long id) {
		 EmployeeDigiratina employeeDigiratina= employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exists" + id));
		 return ResponseEntity.ok(employeeDigiratina); 
	}
	
	// update digiratina employee
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<EmployeeDigiratina> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDigiratina employeeDetails){
		EmployeeDigiratina employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exists:" + id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		employee.setDesignation(employeeDetails.getDesignation());
		
		EmployeeDigiratina updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	// delete digiratina employee
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
		EmployeeDigiratina employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exists:" + id));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Employee Deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
