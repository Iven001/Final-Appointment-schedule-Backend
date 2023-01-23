package com.project.appointment_schedule_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, String> {
    
}
