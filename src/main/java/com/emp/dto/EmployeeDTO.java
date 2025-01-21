package com.emp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDTO {
	
	private long Id;
	
	private String firstName;

	private String lastName;

	private String email;

	private String password;
		
	private String confirmPassword;

	private double salary;

	private String Action;
	
	private Date loginAt;
	
	


}
