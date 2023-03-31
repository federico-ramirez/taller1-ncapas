package com.grupo19.taller1.models.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
	private String username;
	private String password;
	private Date hiringDate;
	private Boolean status;
	private String role;
}
