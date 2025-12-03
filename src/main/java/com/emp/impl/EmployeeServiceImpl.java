package com.emp.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emp.dto.ChangePassword;
import com.emp.dto.EmployeeDTO;
import com.emp.dto.EmployeeLoginDTO;

import com.emp.dto.UserRequest;
import com.emp.entity.EmployeeEntity;
import com.emp.exception.UserCustomException;
import com.emp.repo.EmployeeRepo;
import com.emp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo repo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public EmployeeEntity saveDetails(EmployeeDTO dto) {

		userDetailsValidation(dto);

		// dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		// dto.setConfirmPassword(passwordEncoder.encode(dto.getPassword()));

		EmployeeEntity entity = entityToDTO(dto);

		return repo.save(entity);

	}

//	@Override
//	public EmployeeDTO updateDetails(EmployeeDTO dto) {
//
//		try {
//			EmployeeEntity entity = new EmployeeEntity();
//			entity.setFristName(dto.getFristName());
//			entity.setLastName(dto.getLastName());
//			entity.setEmail(dto.getEmail());
//			entity.setPassword(dto.getPassword());
//			//entity.setSalary(dto.getSalary());
//			entity.setDepartment(dto.getDepartment());
//			repo.save(entity);
//
//		} catch (Exception e) {
//			// TODO: handle exception
//
//		}
//		return dto;
//
//	}

	@Override
	public EmployeeEntity getEmployee(Long id) {

		return repo.findById(id).get();
	}

//
//	@Override
//	public EmployeeEntity updateDetails(EmployeeEntity entity, Long id) {
//		
//		
//		/*
//		 * EmployeeEntity emp=repo.findById(id).get(); if(emp.getId() !=0) {
//		 * emp.setFristName(entity.getFristName());
//		 * emp.setLastName(entity.getLastName()); emp.setEmail(entity.getEmail());
//		 * emp.setDepartment(entity.getDepartment());
//		 * 
//		 * }
//		 */
//		  return repo.save(entity);
//		 
//	
//
//		
//	}

	public EmployeeEntity getEmployee1(Long id) {

		return repo.findById(id).get();
	}

	@Override
	public UserRequest deleteEmployee(Long id) {
		UserRequest request = new UserRequest();
		try {
			Optional<EmployeeEntity> userOptional = repo.findById(id);
			if (userOptional.isPresent()) {
				repo.deleteById(id);
				request.setStatusCode(200);
				request.setMessage(id + " " + "User Successfully Deleted");
				request.setTimestamp(new Date(System.currentTimeMillis()));
			} else {
				request.setStatusCode(404);
				request.setError(id + " " + "User Not Found");
				request.setTimestamp(new Date(System.currentTimeMillis()));

			}

		} catch (Exception e) {
			request.setStatusCode(500);
			request.setError("Error occurred while deleting user: " + e.getMessage());

		}
		return request;

	}

	@Override
	public List<EmployeeEntity> getAllEmployee() {
		return repo.findAll();
	}

	@Override
	public EmployeeEntity findByFristnameAndLastname(String firstName, String lastName) {
		return repo.findByFristnameAndLastname(firstName, lastName);
	}

//convert to Entity to Dto

	public EmployeeEntity entityToDTO(EmployeeDTO dto) {
		EmployeeEntity entity = new EmployeeEntity();
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setConfirmPassword(dto.getConfirmPassword());
		entity.setSalary(dto.getSalary());

		return entity;

	}

	@Override
	public EmployeeDTO employeeLogin(EmployeeLoginDTO loginDTO) {

		if (loginDTO.getEmail() == null || loginDTO.getEmail().isEmpty()) {
			EmployeeEntity entity = new EmployeeEntity();
			entity.setEmail(loginDTO.getEmail());
			repo.save(entity);
		}

		EmployeeEntity findByEmail = repo.findByEmail(loginDTO.getEmail().toLowerCase());
		if (null != findByEmail) {
			// String securePassword = getSecurePassword(loginDTO.getPassword());
			if (findByEmail.getPassword().equals(loginDTO.getPassword())) {
				EmployeeDTO employeeDTO = new EmployeeDTO();
				employeeDTO.setId(findByEmail.getId());
				employeeDTO.setFirstName(findByEmail.getFirstName());
				employeeDTO.setLastName(findByEmail.getLastName());
				employeeDTO.setEmail(findByEmail.getEmail());
				employeeDTO.setPassword(findByEmail.getPassword());
				employeeDTO.setLoginAt(new Date(System.currentTimeMillis()));
				employeeDTO.setSalary(findByEmail.getSalary());
				employeeDTO.setConfirmPassword(findByEmail.getConfirmPassword());
				// employeeDTO.setAction(null);
				return employeeDTO;
			} else {
				throw new UserCustomException("Please Enter Correct Username And Password");
			}

		} else {
			throw new UserCustomException("no such record found");
		}

	}

//	@Override
//	public LoginMesage employeeLogin(EmployeeLoginDTO loginDTO) {
//
//		// String msg = "";
//		EmployeeEntity entity = repo.findByEmail(loginDTO.getEmail());
//		if (entity != null) {
//			String password = loginDTO.getPassword();
//			String encodePassword = entity.getPassword();
//			Boolean isPwdRight = passwordEncoder.matches(password, encodePassword);
//			if (isPwdRight) {
//				Optional<EmployeeEntity> empEntity = repo.findByEmailAndPassword(loginDTO.getEmail(), encodePassword);
//				if (empEntity.isPresent()) {
//					return new LoginMesage(entity.getFristName() + " " + "Succesfully Login", true);
//				} else {
//					return new LoginMesage("Login Failed", false);
//				}
//			} else {
//				return new LoginMesage("Password Not Matched", false);
//			}
//
//		} else {
//			return new LoginMesage("Email not Exists", false);
//		}
//
//	}

	void userDetailsValidation(EmployeeDTO employeeDTO) {
		if (employeeDTO.getFirstName() == null || employeeDTO.getFirstName().isEmpty()) {
			throw new UserCustomException("please provid valid firstName");
		}
		if (employeeDTO.getLastName() == null || employeeDTO.getLastName().isEmpty()) {
			throw new UserCustomException("please provid valid LastName");
		}
		if (employeeDTO.getEmail() == null || employeeDTO.getEmail().isEmpty()) {
			throw new UserCustomException("please provid valid email");
		}

		if (employeeDTO.getPassword() == null || employeeDTO.getPassword().isEmpty()) {
			throw new UserCustomException("please provid valid password");
		}

		EmployeeEntity findByEmail = repo.findByEmail(employeeDTO.getEmail());
		if (null != findByEmail) {
			throw new UserCustomException(" User Already Existed");
		}
		if (!employeeDTO.getPassword().equals(employeeDTO.getConfirmPassword())) {
			throw new UserCustomException("Password Confirm Password Not Matched...!");
		}

	}

	@Override
	public EmployeeEntity updateDetails(EmployeeEntity empEntity, Long Id) {
		return repo.save(empEntity);
	}

	@Override
	public void changePassword(String email, ChangePassword changePassword) {
		EmployeeEntity entity = repo.findByEmail(email);
		if (entity == null) {
			throw new UserCustomException("User not found with email: " + email);
		}

		if (changePassword.getNewPassword() == null || changePassword.getNewPassword().isEmpty()) {
			throw new UserCustomException("New password cannot be empty");
		}
		if (!changePassword.getNewPassword().equals(changePassword.getConfirmPassword())) {
			throw new UserCustomException("New password and confirm password do not match");
		}

		entity.setPassword(changePassword.getNewPassword());
		entity.setConfirmPassword(changePassword.getConfirmPassword());
		repo.save(entity);

	}

}
