package com.project.appointment_schedule_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.appointment_schedule_management.dto.JwtRequest;
import com.project.appointment_schedule_management.dto.JwtResponse;
import com.project.appointment_schedule_management.service.JwtService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JwtController {
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping({"/authenticate"})
	public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		return jwtService.createJwtToken(jwtRequest);
	}
}
