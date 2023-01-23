package com.project.appointment_schedule_management.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="team")
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name= "team_id")
    private String teamId;
    @Column(name= "team_name")
    private String teamName;
    @Column(name= "department")
    private String departmentId;
    public String getTeamId() {
        return teamId;
    }
    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
  

    
    
}
