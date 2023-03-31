package com.grupo19.taller1.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.time.*;

import com.grupo19.taller1.models.dto.LoginDTO;
import com.grupo19.taller1.models.entities.Employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class EmployeeController {
	
	private static List<Employee> employees = new ArrayList<>();
	private Boolean validEmp = false;
	private Boolean isAdmin = false;
	private String username = "";
	private String password = "";
	
	
	static {
		employees.add(new Employee("AA567890", "Maria", "Campos", "1234", "2023-03-23", true, "User"));
		employees.add(new Employee("AA567891", "Luis", "Alonso", "A01235", "2023-01-15", false, "Admin"));
	}
	
	@GetMapping("/")
	public String getIndexPage(Model model) {
		return "index";
	}
	
	@PostMapping("/login")
    public String saveSession(@ModelAttribute LoginDTO login) {
		employees.stream()
			.forEach((employee) -> {
				username = login.getUsername().toString();
				password = login.getPassword().toString();
				
				// Validations for username
				if (employee.getUsername().equals(username) && employee.getPassword().equals(password) 
						&& employee.getStatus().equals(true))
				{
					validEmp = true;
				}
			});
		
		if(validEmp == true) {
			return "redirect:/index/employees";
		} else {
			return "not-found";
		}
    }
	
	
	@GetMapping("/employees")
	public String getEmployees(Model model) {
		String time = Calendar.getInstance().getTime().toString();
		isAdmin = false;
		employees.stream().forEach((employee) -> {
			if (employee.getRole().equals("Admin") && 
					employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
				model.addAttribute("employees", employees);
				isAdmin = true;
			}
			else {
				model.addAttribute("time", time);
				isAdmin = false;
			}
		});

		if (isAdmin == true) {
			return "dashboard";
		} else {
			return "user";
		}
		
	}
}
