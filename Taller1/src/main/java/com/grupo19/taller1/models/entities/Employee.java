package com.grupo19.taller1.models.entities;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
	private String username;
	private String name;
	private String lastName;
	private String password;
	private String hiringDate;
	private Boolean status;
	private String role;
}
