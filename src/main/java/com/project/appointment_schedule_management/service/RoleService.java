package com.project.appointment_schedule_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.appointment_schedule_management.dao.RoleRepository;
import com.project.appointment_schedule_management.model.Role;



@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepo;
	
	public Role createNewRole(Role role) {
		return roleRepo.save(role);
	}
}

