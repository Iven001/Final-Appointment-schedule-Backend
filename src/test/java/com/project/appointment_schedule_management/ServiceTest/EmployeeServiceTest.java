package com.project.appointment_schedule_management.serviceTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.appointment_schedule_management.dao.EmployeeRepository;
import com.project.appointment_schedule_management.dto.EmployeeDto;
import com.project.appointment_schedule_management.model.Employee;
import com.project.appointment_schedule_management.service.EmployeeServiceImpl;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository eRepository;

    @InjectMocks
    private EmployeeServiceImpl eService;
    
    private static Employee employee;

    private static List<Employee> employees;

    @BeforeAll
    public static void runBeforeAll(){

        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("Hla Yamin");
        employee.setPosition("member");
        employee.setTeam("shell");

        Employee employee2 = new Employee();
        employee2.setEmployeeId(2);
        employee2.setEmployeeName("John");
        employee2.setPosition("org");
        employee2.setTeam("Cobol");

        employees = new ArrayList<>();

        Collections.addAll(employees, employee, employee2);

    }

    @Test
    public void getAllEmployeesTest(){
        when(this.eRepository.findAll()).thenReturn(employees);

        assertEquals(this.eService.getAllEmployees().size(), employees.size());
        verify(this.eRepository, times(1)).findAll();
    }

    @Test
    public void getEmployeeByIdTest(){
        when(this.eRepository.findById(1)).thenReturn(Optional.of(employee));
        assertNotNull(this.eService.findByEmployee(1, "hla"));

        // when(eRepository.findById(anyInt())).thenReturn(Optional.empty());
        // assertThrows(EntityNotFoundException.class, eRepository.findById(1));

        
        // when(this.eRepository.findById(1)).thenReturn(Optional.of(employee));
        // assertNotNull(this.eService.findById);
    }

    @Test
    public void saveEmployeeTest(){
        when(this.eRepository.save(employee)).thenReturn(employee);
        assertNotNull(this.eRepository.save(employee));
    }

    @Test
    public void deleteEmployeeTest(){
        doNothing().when(eRepository).deleteById(anyInt());

        eService.deleteEmployee(1);
        verify(eRepository, times(1)).deleteById(1);
        verifyNoMoreInteractions(eRepository);

        // when(eRepository.deleteById(employee.getEmployeeId())).thenReturn(Optional.of(employee)));
        

    //    Employee expected  = new Employee();
    //     expected.setEmployeeId(1);
    //     eService.deleteEmployee(expected.getEmployeeId());
    //     Mockito.verify(eService).deleteEmployee(expected.getEmployeeId());
    //     verify(eService).deleteEmployee(expected.getEmployeeId());
    }

    @Test
    public void getEmployeeByEmployeeNameTest(){
        when(this.eRepository.findByEmployeeName("JJ")).thenReturn(employee);
        assertNotNull(this.eService.findByEmployee(1, "JJ"));
    }

    @Test
    public void findByEmployeeTest(){
        when(this.eRepository.findById(1)).thenReturn(Optional.of(employee));
        assertNotNull(this.eService.findByEmployee(1, "AA"));
    }

}
