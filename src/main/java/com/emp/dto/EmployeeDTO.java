package com.emp.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDTO {
	
	private long Id;
	
	private String fristName;

	private String lastName;

	private String email;

	private String password;
	
	private String department;
	
	private long salary;

	private String action;
	
	private Date loginAt;



}
