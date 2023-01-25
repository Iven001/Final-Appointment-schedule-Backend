package com.project.appointment_schedule_management.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.project.appointment_schedule_management.dao.TeamRepository;
import com.project.appointment_schedule_management.model.Team;
import com.project.appointment_schedule_management.service.TeamServiceImpl;

@SpringBootTest
public class TeamServiceTest {
	@Mock
    private TeamRepository teamRepo;

    @InjectMocks
    private TeamServiceImpl teamService;

    private static Team team;

    private static List<Team> teams;

    @BeforeAll
    public static void runBeforeAll(){
        
        team = new Team();
        team.setTeamId("S001");
        team.setTeamName("Radiance");
        team.setDepartmentId("ShellScript001");

        Team team2 = new Team();
        team2.setTeamId("S002");
        team2.setTeamName("Black");
        team2.setDepartmentId("OffShow001");

        teams = new ArrayList<>();

        Collections.addAll(teams, team, team2);

    }

    @Test
    public void getAllTeams(){
        when(this.teamRepo.findAll()).thenReturn(teams);
        assertEquals(this.teamService.getAllTeams().size(), teams.size());
        verify(this.teamRepo, times(1)).findAll();

    }

}
