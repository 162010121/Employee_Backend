package com.emp.dto;

import lombok.Data;

@Data
public class EmployeeLogout {

	
	private String email;

	@Override
	public String toString() {
		return "EmployeeLogout [email=" + email + "]";
	}
}
