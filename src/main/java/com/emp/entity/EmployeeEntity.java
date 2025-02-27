package com.emp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Table(name = "employee")
@Data
@Entity
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private long Id;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@NotNull
	@Column(name = "email")
	private String email;

	@Column(name = "action")
	private String action;

	@Column(name = "password")
	private String password;

	@Column(name = "salary")
	private double salary;

	@Column(name = "confirmPassword")
	private String confirmPassword;

}
