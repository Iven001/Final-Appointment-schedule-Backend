package com.project.appointment_schedule_management.dto;

public class UserSearch {

    private int u_id;
    private String name;
    private byte[] image_data;
    private int imgId;
    private String teamName;
    private String departmentName;
    private String imageName;
    public int getU_id() {
        return u_id;
    }
    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public byte[] getImage_data() {
        return image_data;
    }
    public void setImage_data(byte[] image_data) {
        this.image_data = image_data;
    }
    public int getImgId() {
        return imgId;
    }
    public void setImgId(int imgId) {
        this.imgId = imgId;
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
    public String getImageName() {
        return imageName;
    }
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
    
    
    
    
}
