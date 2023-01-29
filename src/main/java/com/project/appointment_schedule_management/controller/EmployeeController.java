package com.project.appointment_schedule_management.controller;

import java.util.List;
import java.util.Optional;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.appointment_schedule_management.Interface.EmployeeInfo;
import com.project.appointment_schedule_management.Interface.EmployeeInter;
import com.project.appointment_schedule_management.dao.EmployeeRepository;
import com.project.appointment_schedule_management.dto.EmployeeDto;
import com.project.appointment_schedule_management.model.Employee;
import com.project.appointment_schedule_management.model.User;
import com.project.appointment_schedule_management.service.EmployeeService;
import com.project.appointment_schedule_management.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    // @Autowired
    // EmployeeService empService;

    @Autowired
    UserService userService;

    private final EmployeeService empService;
    private final EmployeeRepository empRepository;

    public EmployeeController(EmployeeService empService, EmployeeRepository empRepository) {
        this.empService = empService;
        this.empRepository = empRepository;
    }

    @PostMapping({ "/createEmployee" })
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeDto empDto) {
        Employee emp = new Employee();

        Optional<Employee> oldEmp = empRepository.findByEmployeeId(empDto.getEmployeeId());
        int i = empDto.getEmployeeId();
        try {
            if (oldEmp.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }else{
                emp.setEmployeeId(empDto.getEmployeeId());
                emp.setEmployeeName(empDto.getEmployeeName());
                emp.setPosition(empDto.getPosition());
                emp.setTeam(empDto.getTeam());
                empService.saveEmployee(emp);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(emp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int empId) {
        try {
            User user = userService.getUserByEmpId(empId);
            userService.deleteUser(user.getUserId());
            empService.deleteEmployee(empId);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/setEmployeeUpdate/{empId}")
    public ResponseEntity<?> setEmployeeUpdate(@PathVariable int empId) {
        EmployeeDto empDto = new EmployeeDto();
        try {
            Employee emp = new Employee();
            emp = empService.getEmployeeById(empId);
            empDto.setEmployeeId(emp.getEmployeeId());
            empDto.setEmployeeName(emp.getEmployeeName());
            empDto.setPosition(emp.getPosition());
            empDto.setTeam(emp.getTeam());
            return ResponseEntity.status(HttpStatus.CREATED).body(empDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Employee Id not found!");
        }
    }

    @PutMapping("/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto empDto) {
        try {
            Employee emp = new Employee();
            emp = empService.getEmployeeById(empDto.getEmployeeId());
            if (emp != null) {
                emp.setEmployeeName(empDto.getEmployeeName());
                emp.setPosition(empDto.getPosition());
                emp.setTeam(empDto.getTeam());
                empService.saveEmployee(emp);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(emp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getAllEmployees")
    public ResponseEntity<?> getAllEmployees() {
        try {
            List<Employee> list = empService.getAllEmployees();
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/findEmployeeById")
    public ResponseEntity<?> findEmployeeById(@RequestParam int empId) {
        try {
            Employee emp = empService.getEmployeeById(empId);
            return ResponseEntity.status(HttpStatus.OK).body(emp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/findEmployeeByName")
    public ResponseEntity<?> findEmployeeByName(@RequestParam String empName) {
        try {
            Employee emp = empService.getEmployeeByEmployeeName(empName);
            return ResponseEntity.status(HttpStatus.OK).body(emp);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/searchEmployee")
    public ResponseEntity<?> searchEmployee (@RequestParam(required = false) int empId,@RequestParam(required = false) String empName) {
        try {
          //  String name = Integer.toString(empName);
            List<EmployeeInter> list = empService.findByEmployee(empId, empName);
            return ResponseEntity.status(HttpStatus.OK).body(list);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @GetMapping("/getEmployees")
    public ResponseEntity<?> getEmployeeInfo () {
        try {
 
            List<EmployeeInfo> list = empService.getEmployeeInfo();
            return ResponseEntity.status(HttpStatus.OK).body(list);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
