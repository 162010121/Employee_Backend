package com.emp.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.emp.dto.EmployeeDTO;
import com.emp.dto.EmployeeLoginDTO;
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
		EmployeeEntity entity=entityToDTO(dto);
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
	public void deleteEmployee(Long id) {

		repo.deleteById(id);
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
		entity.setFristName(dto.getFristName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());
		entity.setDepartment(dto.getDepartment());
		// entity.setSalary(dto.getSalary());
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
				employeeDTO.setFristName(findByEmail.getFristName());
				employeeDTO.setLastName(findByEmail.getLastName());
				employeeDTO.setEmail(findByEmail.getEmail());
				employeeDTO.setPassword(findByEmail.getPassword());
				employeeDTO.setLoginAt(new Date(System.currentTimeMillis()));
				employeeDTO.setDepartment(findByEmail.getDepartment());
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
		if (employeeDTO.getFristName() ==null|| employeeDTO.getFristName().isEmpty()) {
			throw new UserCustomException("please provid valid firstName");
		}
		if (employeeDTO.getLastName() ==null|| employeeDTO.getLastName().isEmpty()) {
			throw new UserCustomException("please provid valid LastName");
		}
		if (employeeDTO.getEmail() ==null|| employeeDTO.getEmail().isEmpty()) {
			throw new UserCustomException("please provid valid email");
		}

		if (employeeDTO.getPassword()==null || employeeDTO.getPassword().isEmpty()) {
			throw new UserCustomException("please provid valid password");
		}

		EmployeeEntity findByEmail = repo.findByEmail(employeeDTO.getEmail());
		if (null != findByEmail) {
			throw new UserCustomException(" User Already Existed");
		}
	}

	@Override
	public EmployeeEntity updateDetails(EmployeeEntity empEntity, Long Id) {
		return repo.save(empEntity);
	}

}
