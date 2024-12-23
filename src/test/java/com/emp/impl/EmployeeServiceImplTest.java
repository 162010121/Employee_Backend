package com.emp.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emp.dto.EmployeeDTO;
import com.emp.entity.EmployeeEntity;
import com.emp.repo.EmployeeRepo;

@RunWith(SpringJUnit4ClassRunner.class)

public class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepo repository;

	@InjectMocks
	private EmployeeServiceImpl service;

	AutoCloseable autoCloseable;

	EmployeeDTO employeeDTO;

	/*
	 * @BeforeEach void setUp() {
	 * 
	 * autoCloseable = MockitoAnnotations.openMocks(service); employeeDTO = new
	 * EmployeeDTO();
	 * 
	 * }
	 */

	@Test
	public void testInsertEmployee() {

		EmployeeEntity entity = mock(EmployeeEntity.class);
		EmployeeDTO employee = mock(EmployeeDTO.class);
		mock(EmployeeRepo.class);

		when(repository.save(entity)).thenReturn(entity);
		assertThat(service.saveDetails(employee));

	}

	@Test
	public void testInsertUserException() throws Exception {

		EmployeeDTO dto = mock(EmployeeDTO.class);
		EmployeeEntity entity = mock(EmployeeEntity.class);

		mock(EmployeeRepo.class);

//		  dto.setId(1L); 
//		  dto.setFristName("Praveen"); 
//		  dto.setLastName("Pustera");
//		  dto.setEmail("Praveen@email.com"); 
//		  //dto.setPassword("$2a$10$oqbjHIFaGgWJ4DNnsUZN0OjroirAADz.CBdSoKsBR8c4DCbfCz6qC");
//		  dto.setSalary(10000); dto.setDepartment("IA");

		when(repository.save(Mockito.any())).thenReturn(entity);
		EmployeeEntity empDTO = service.saveDetails(dto);
		assertNotEquals(entity, empDTO);

	}

}
