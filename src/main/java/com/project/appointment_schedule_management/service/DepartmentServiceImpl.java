package com.project.appointment_schedule_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.appointment_schedule_management.dao.DepartmentRepository;
import com.project.appointment_schedule_management.model.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository departmentRepository;
    
    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }




    @Override
    public List<Department> getAllDepartments() {
     List<Department> list = departmentRepository.findAll();
        return list;
    }
    
}
