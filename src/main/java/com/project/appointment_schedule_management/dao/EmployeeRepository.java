package com.project.appointment_schedule_management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.Interface.EmployeeInfo;
import com.project.appointment_schedule_management.Interface.EmployeeInter;
import com.project.appointment_schedule_management.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Employee findByEmployeeName (String name);
    // Optional<Employee> getEmployeeById (int employeeId);

    Optional<Employee> findByEmployeeId(int employeeId);

    @Query(value="Select e.employee_id as employeeId,e.employee_name as employeeName,e.team as team,e.position as position "+
    "From appointment.employee e "+
    "Where e.employee_id=:empId or e.employee_name=:empName ",nativeQuery = true)
    List<EmployeeInter> findByEmployee (int empId,String empName);
    
    
    @Query(value="Select e.employee_id as employeeId,e.employee_name as employeeName,e.team as team,e.position as position,t.team_name as teamName,d.department_name as departmentName " +
    "From appointment.employee e,appointment.team t,appointment.department d " +
    "Where e.employee_id=e.employee_id and e.team=t.team_id and t.department=d.department_id",nativeQuery= true)
    List<EmployeeInfo> getEmployeeInfo ();

    // @Query("Select t From Employee e "+ 
    // "Where e.employeeId=:empId or upper (e.employeeName) like upper(concat('%',?1,'%'))")
    // List<Employee> findByEmployee (int empId,String empName);
    
}
