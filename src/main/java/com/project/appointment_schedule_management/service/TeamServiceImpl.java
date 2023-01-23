package com.project.appointment_schedule_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.appointment_schedule_management.dao.TeamRepository;
import com.project.appointment_schedule_management.model.Team;

@Service
public class TeamServiceImpl implements TeamService {

   private final TeamRepository teamRepository;

   
    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
}



    @Override
    public List<Team> getAllTeams() {
        List<Team> list = teamRepository.findAll();
        return list;
    }
    
}
