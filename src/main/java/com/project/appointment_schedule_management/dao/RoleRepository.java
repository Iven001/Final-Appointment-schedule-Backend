package com.project.appointment_schedule_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}