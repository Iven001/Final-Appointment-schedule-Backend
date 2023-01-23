package com.project.appointment_schedule_management.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	@Id
	private String roleName;
	private String roleDescription;
//	@ManyToMany(mappedBy = "role")
//	private List <User> user;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDescription() {
		return roleDescription;
	}
	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}
	
//	public List<User> getUser() {
//		return user;
//	}
//	public void setUser(List<User> user) {
//		this.user = user;
//	}
	
	
}
