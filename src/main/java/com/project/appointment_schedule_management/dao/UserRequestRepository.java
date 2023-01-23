package com.project.appointment_schedule_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.model.UserRequest;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Integer> {
    
    
}
