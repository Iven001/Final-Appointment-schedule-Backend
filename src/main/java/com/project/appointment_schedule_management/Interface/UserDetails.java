package com.project.appointment_schedule_management.Interface;


public interface UserDetails {
// Integer userId=0;
// String uname="";
// String name="";
// Integer imageId=0;
// byte[] image_data=new byte[]{};
// String teamName="";
// String departmentName="";
    int getUserId();
    String getUname();
    String getName();
    byte[] getImageData();
    int getImageId();
    String getTeamName();
    String getDepartmentName();
    
    // void setUserId(Integer userId);
    // void setUname(String uname);
    // void setName(String name);
    // void setImageData(byte[] image_data);
    // void setImageId(Integer imageId);
    // void setTeamName(String teamName);
    // void setDepartmentName(String deaprtmentName);
}
