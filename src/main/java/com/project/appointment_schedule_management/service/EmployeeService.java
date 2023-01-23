package com.project.appointment_schedule_management.service;

import java.util.List;


import com.project.appointment_schedule_management.model.Employee;

public interface EmployeeService {

    Employee getEmployeeById (int employeeId);

    Employee getEmployeeByEmployeeName (String empName);

    void saveEmployee (Employee emp);

    void deleteEmployee (int empId);

    List <Employee> getAllEmployees ();

}
