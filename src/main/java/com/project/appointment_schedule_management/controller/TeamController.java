package com.project.appointment_schedule_management.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.appointment_schedule_management.dao.TeamRepository;
import com.project.appointment_schedule_management.service.TeamService;

@CrossOrigin(origins = "http://localhost:4200" )
@RestController
@RequestMapping("/team" )
public class TeamController {

    private final TeamRepository teamRepository;
    private final TeamService teamService;
    public TeamController(TeamRepository teamRepository, TeamService teamService) {
        this.teamRepository = teamRepository;
        this.teamService = teamService;
    }

    @GetMapping("/allTeam")
    public ResponseEntity<?> getAllTeams () {

        try {
           // Team team = teamService.getAllTeams();
         return ResponseEntity.status(HttpStatus.OK).body(teamService.getAllTeams());

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    
    
}
