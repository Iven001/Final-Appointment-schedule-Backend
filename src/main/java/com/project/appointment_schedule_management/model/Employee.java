package com.project.appointment_schedule_management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "employee")
public class Employee {

	@Id
    @Column(name = "employee_id",nullable = false, updatable = false)
    private int employeeId;
	
    @Column(name = "employee_name")
    private String employeeName;
    
    @Column(name = "team")
    private String team;
   
	@Column(name = "position")
    private String position;
    
   
	public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    
    @Override
   	public String toString() {
   		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", team=" + team
   				+ ", position=" + position + "]";
   	}
    
    public Employee() {
		
	}
	/*
	 * public Employee(int employeeId, String employeeName, String team, String
	 * position, String authority) { super(); this.employeeId = employeeId;
	 * this.employeeName = employeeName; this.team = team; this.position = position;
	 * this.authority = authority; }
	 */


}
