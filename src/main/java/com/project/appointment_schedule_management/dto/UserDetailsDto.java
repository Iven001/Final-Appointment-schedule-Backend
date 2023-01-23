package com.project.appointment_schedule_management.dto;

import java.io.Serializable;

import lombok.Data;

//@Data
public class UserDetailsDto implements Serializable {

    private int userId;
    private String uname;
    private String name;
    private byte[] imageData;
    private int imageId;
    private String teamName;
    private String departmentName;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public byte[] getImageData() {
        return imageData;
    }
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getTeamName() {
        return teamName;
    }
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public UserDetailsDto(int userId, String uname, String name, byte[] imageData, int imageId, String teamName,
            String departmentName) {
        this.userId = userId;
        this.uname = uname;
        this.name = name;
        this.imageData = imageData;
        this.imageId = imageId;
        this.teamName = teamName;
        this.departmentName = departmentName;
    }



    

    

    
    
  
    
    
    
}
