package com.project.appointment_schedule_management.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
    
}
