package com.emp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.dto.ChangePassword;
import com.emp.dto.EmployeeDTO;
import com.emp.dto.EmployeeLoginDTO;
import com.emp.dto.UserRequest;
import com.emp.entity.EmployeeEntity;
import com.emp.repo.EmployeeRepo;
import com.emp.service.EmployeeService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@Autowired
	private AuthenticationManager authenticatiManager;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private EmployeeRepo repo;

	@PostMapping("/register")
	public ResponseEntity<EmployeeEntity> saveDetails(@RequestBody @Valid EmployeeDTO dto) {
		EmployeeEntity entity2 = service.saveDetails(dto);
		return new ResponseEntity<>(entity2, HttpStatus.CREATED);

	}

	@PutMapping("/updateEmployee/{Id}")
	public EmployeeEntity updateEmployee(@RequestBody EmployeeEntity employeeEntity, @PathVariable("Id") Long Id) {
		EmployeeEntity entity2 = service.updateDetails(employeeEntity, Id);
		return entity2;

	}

//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@RequestBody EmployeeLoginDTO loginDTO) {
//		Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//
//		service.employeeLogin(loginDTO);
//		return new ResponseEntity<>( HttpStatus.OK);
//
//	}

	@PostMapping("/login")
	public ResponseEntity<EmployeeDTO> loginEmployee(@RequestBody EmployeeLoginDTO loginDTO) {

		EmployeeDTO employee = service.employeeLogin(loginDTO);
		return new ResponseEntity<>(employee, HttpStatus.OK);

	}

	@GetMapping("/getEmployee/{Id}")
	public ResponseEntity<EmployeeEntity> getEmployee(@PathVariable("Id") Long Id)

	{
		EmployeeEntity eEntity = service.getEmployee(Id);

		return new ResponseEntity<>(eEntity, HttpStatus.OK);

	}

	@DeleteMapping("/deleteEmployee/{Id}")
	public UserRequest deleteEmployee(@PathVariable("Id") Long Id) {
		UserRequest user = service.deleteEmployee(Id);
		return user;

	}

	@GetMapping("/getAllEmployee")
	public ResponseEntity<List<EmployeeEntity>> getAll() {

		List<EmployeeEntity> entities = service.getAllEmployee();

		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping("/getEmployeeByName/{fristName}/{lastName}")
	public ResponseEntity<EmployeeEntity> getByNames(@PathVariable("fristName") String fristName,
			@PathVariable("lastName") String lastName) {

		EmployeeEntity entities = service.findByFristnameAndLastname(fristName, lastName);
		return new ResponseEntity<>(entities, HttpStatus.OK);

	}

//	@PostMapping("/login")
//	public ResponseEntity<EmployeeDTO> loginUser(@RequestBody EmployeeLoginDTO loging)
//	{
//		EmployeeDTO login=service.employeeLogin(loging);
//		return new ResponseEntity<>(login,HttpStatus.OK);
//	}

	@PutMapping("/changePassword/{email}")
	public ResponseEntity<String> changePassword(@PathVariable("email") String email,
			@RequestBody ChangePassword changePassword) {
		service.changePassword(email, changePassword);
		return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
	}
	

}
