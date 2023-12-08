package com.emp.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.emp.dto.EmployeeDTO;
import com.emp.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	@Mock
	private EmployeeService service;
	

	@InjectMocks
	private EmployeeController controller;
	
	@Test
	@DisplayName("Should save the Employee Object")
    void testCreateEmployee() throws Exception
    {
    	EmployeeDTO empDTO=mock(EmployeeDTO.class);
    	when(service.saveDetails(empDTO)).thenReturn(empDTO);
    	ResponseEntity<EmployeeDTO> responce=controller.saveDetails(empDTO);
    	assertEquals(HttpStatus.CREATED, responce.getStatusCode());	

    }
	
	@Test
	void testUpdateEmployee()throws Exception
	{
		EmployeeDTO empDTO= mock(EmployeeDTO.class);
		when(service.updateDetails(empDTO)).thenReturn(empDTO);
		ResponseEntity<EmployeeDTO> responce=controller.updateEmployee(empDTO);
    	assertEquals(HttpStatus.OK, responce.getStatusCode());	

		
	}

}
