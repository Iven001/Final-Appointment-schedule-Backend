package com.project.appointment_schedule_management.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="department")
public class Department implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name= "department_id",nullable = false, updatable = false)
    private String departmentId;
    @Column(name= "department_name")
    private String departmentName;
    
    public String getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    
    
}
