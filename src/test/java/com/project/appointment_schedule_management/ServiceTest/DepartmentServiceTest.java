package com.project.appointment_schedule_management.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.appointment_schedule_management.dao.DepartmentRepository;
import com.project.appointment_schedule_management.model.Department;
import com.project.appointment_schedule_management.model.Team;
import com.project.appointment_schedule_management.service.DepartmentServiceImpl;

@SpringBootTest
public class DepartmentServiceTest {
    @Mock
    private DepartmentRepository depRepo;

    @InjectMocks
    private DepartmentServiceImpl depService;

    private static Department dep;
    
    private static List<Department> deps;

    @BeforeAll
    public static void runBeforeAll(){

        dep = new Department();
        dep.setDepartmentId("ShellScript001");
        dep.setDepartmentName("ShellScript");

        Department dep2 = new Department();
        dep2.setDepartmentId("OffShow001");
        dep2.setDepartmentName("OffShow");

        deps = new ArrayList<>();

        Collections.addAll(deps, dep, dep2);

    }

    @Test
    public void getAllDepartmentsTest(){
        when(this.depRepo.findAll()).thenReturn(deps);
        assertEquals(this.depService.getAllDepartments().size(), deps.size());
        verify(this.depRepo, times(1)).findAll();
    }




}
