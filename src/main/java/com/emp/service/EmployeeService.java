package com.emp.service;

import java.util.List;

import com.emp.dto.EmployeeDTO;
import com.emp.dto.EmployeeLoginDTO;
import com.emp.dto.EmployeeLogout;
import com.emp.entity.EmployeeEntity;

public interface EmployeeService {

//	public EmployeeDTO saveDetails(EmployeeDTO entity);
//
//	public EmployeeDTO updateDetails(EmployeeDTO entity, Long id);
//
//	public EmployeeDTO getEmployee(Long id);
//
//	public void deleteEmployee(Long id);
//
//	public List<EmployeeDTO> getAllEmployee();
//
//	public EmployeeDTO findByFristnameAndLastname(String firstName, String lastName);
	
	
	
	public EmployeeDTO saveDetails(EmployeeDTO empDTO);

	public EmployeeEntity updateDetails(EmployeeEntity empEntity,Long Id);

	public EmployeeEntity getEmployee(Long id);

	public void deleteEmployee(Long id);

	public List<EmployeeEntity> getAllEmployee();

	public EmployeeEntity findByFristnameAndLastname(String firstName, String lastName);
	
	//public LoginMesage employeeLogin(EmployeeEntity loginDTO);
	
	public EmployeeDTO employeeLogin(EmployeeLoginDTO loginDTO);
	
	public String logout(EmployeeLogout logout);

}
