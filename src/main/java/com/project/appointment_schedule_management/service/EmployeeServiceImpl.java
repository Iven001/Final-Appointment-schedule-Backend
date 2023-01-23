package com.project.appointment_schedule_management.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.appointment_schedule_management.dao.EmployeeRepository;
import com.project.appointment_schedule_management.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    @Autowired
    Employee emp;

    @Override
    public Employee getEmployeeById(int employeeId) {
        return empRepo.findById(employeeId).get();
    }

    @Override
    public void saveEmployee(Employee emp) {
        empRepo.save(emp);
    }

    @Override
    public void deleteEmployee(int empId) {
        empRepo.deleteById(empId);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> list = empRepo.findAll();
        return list;
    }

    @Override
    public Employee getEmployeeByEmployeeName(String empName) {
       return empRepo.findByEmployeeName(empName);
    }

}
