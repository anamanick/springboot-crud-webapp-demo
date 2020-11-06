package com.doublea.ema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.doublea.ema.model.Employee;
import com.doublea.ema.service.EmployeeService;

@Controller
public class EmployeeController {

	//Display List of employee


	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String viewHomePage(Model model) {

		return findPaginated(1, model);
	}

	@GetMapping("/showNewEmployeeForm")
	public String showEmployeeForm(Model model) {

		Employee employee = new Employee();
		model.addAttribute("employee", employee);

		return "new_employee";

	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee){
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForEmployeeUpdate(@PathVariable (value="id") long id, Model model) {
		
		Employee employee = employeeService.getEmployeeById(id);
		
		model.addAttribute("employee", employee);
		
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value="id") long id) {
		
		this.employeeService.deleteEmployeeById(id);
		
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, Model model) {
		
		int pageSize = 5;
		
		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
		
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmployees", listEmployees);
		
		return "index";
	}
	

}
