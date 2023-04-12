package com.Infoware.employee.controller;

import java.util.ArrayList;
import java.util.List;

import com.Infoware.employee.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Infoware.employee.model.EmployeeData;
import com.Infoware.employee.model.EmployeeForm;
import com.Infoware.employee.pojo.EmployeePojo;
import com.Infoware.employee.service.ApiException;
import com.Infoware.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
public class EmployeeApiController {

	@Autowired
	private EmployeeService service;
	@Autowired
	private EmployeeDto dto;

	@ApiOperation(value = "Adds an employee")
	@RequestMapping(path = "/api/employee", method = RequestMethod.POST)
	public void add(@RequestBody EmployeeForm form) throws ApiException {
		dto.add(form);
	}

	@ApiOperation(value = "Deletes and employee")
	@RequestMapping(path = "/api/employee/{id}", method = RequestMethod.DELETE)
	// /api/1
	public void delete(@PathVariable int id) {
		dto.delete(id);
	}

	@ApiOperation(value = "Gets an employee by ID")
	@RequestMapping(path = "/api/employee/{id}", method = RequestMethod.GET)
	public EmployeeData get(@PathVariable int id) throws ApiException {
		return dto.get(id);
	}

	@ApiOperation(value = "Gets list of all employees")
	@RequestMapping(path = "/api/employee", method = RequestMethod.GET)
	public List<EmployeeData> getAll() {

		return dto.getAll();
	}

	@ApiOperation(value = "Gets total no of employees")
	@RequestMapping(path = "/api/employee/total", method = RequestMethod.GET)
	public int totalEmployees() {
		return dto.totalEmployees();
	}
	@ApiOperation(value = "Gets list of employees of a given page")
	@RequestMapping(path = "/api/employee/{pageNo}/getLimited/{name}", method = RequestMethod.GET)
	public List<EmployeeData> getLimited(@PathVariable int pageNo, @PathVariable String name) {
		if(name.equals("*"))
			name="";
		return dto.getLimited(pageNo, name);
	}
	@ApiOperation(value = "Updates an employee")
	@RequestMapping(path = "/api/employee/{id}", method = RequestMethod.PUT)
	public void update(@PathVariable int id, @RequestBody EmployeeForm f) throws ApiException {

		dto.update(id, f);
	}
	

}
