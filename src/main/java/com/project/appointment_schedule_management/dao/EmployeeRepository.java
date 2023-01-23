package com.project.appointment_schedule_management.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmployeeName (String name);
    // Optional<Employee> getEmployeeById (int employeeId);
    
}
