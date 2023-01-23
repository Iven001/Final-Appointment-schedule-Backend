package com.project.appointment_schedule_management.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.appointment_schedule_management.dao.DepartmentRepository;
import com.project.appointment_schedule_management.model.Department;
import com.project.appointment_schedule_management.service.DepartmentService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/department" )
public class DepartmentController {

    private final DepartmentRepository departmentRepository;
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentRepository departmentRepository, DepartmentService departmentService) {
        this.departmentRepository = departmentRepository;
        this.departmentService = departmentService;
    }

    @GetMapping("/alldep")
    public ResponseEntity<?> getAllTeam () {

        try {
           List<Department> departments = departmentService.getAllDepartments();
         return ResponseEntity.status(HttpStatus.OK).body(departments);

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
    
    
}
