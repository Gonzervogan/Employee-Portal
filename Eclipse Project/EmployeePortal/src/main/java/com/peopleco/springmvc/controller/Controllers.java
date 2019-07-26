package com.peopleco.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.peopleco.springmvc.model.*;
import com.peopleco.springmvc.service.*;

@CrossOrigin(origins = "*")
@RestController
public class Controllers {
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(value="/authenticate",method=RequestMethod.POST)
	public ResponseEntity<Boolean> authenticate(@RequestBody LoginRequest request)
	{
		if(employeeService.authenticateLogin(request.getUsername(),request.getPassword()))
			return new ResponseEntity<>(true,HttpStatus.OK);
		return new ResponseEntity<>(false,HttpStatus.OK);
	}
	
	@RequestMapping(value="/logout/{username}",method=RequestMethod.GET)
	public ResponseEntity<Boolean> logout(@PathVariable("username") String username)
	{
		username=username.replaceAll("@", ".");
		if(employeeService.logout(username))
			return new ResponseEntity<>(true,HttpStatus.OK);
		return new ResponseEntity<>(false,HttpStatus.OK);
	}
	
	@RequestMapping(value="/profile/{username}",method=RequestMethod.GET)
	public ResponseEntity<ProfileResponse> getProfile(@PathVariable("username") String username)
	{
		username=username.replaceAll("@", ".");
		ProfileResponse resp=employeeService.getProfileResponse(username);
		if(resp==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(value="/company/{username}",method=RequestMethod.GET)
	public ResponseEntity<CompanyDetailsResponse> getCompanyDetails(@PathVariable("username") String username)
	{
		username=username.replaceAll("@", ".");
		CompanyDetailsResponse resp=companyService.getCompanyDetailsResponse(username);
		if(resp==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(resp,HttpStatus.OK);
	}
}
