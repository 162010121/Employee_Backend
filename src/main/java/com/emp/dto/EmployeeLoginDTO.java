package com.emp.dto;

import lombok.Data;

@Data
public class EmployeeLoginDTO {

	private String email;
	
	private String password;

	public EmployeeLoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	
}
