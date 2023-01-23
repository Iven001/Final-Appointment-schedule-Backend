package com.project.appointment_schedule_management.dto;

import java.util.List;

import com.project.appointment_schedule_management.model.User;

public class AddMembers {

    private int scheduleId;
    private int ownerId;
    private List<User> membersList;
    private int currentUserId;
    private int addUserId;
    public int getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    public List<User> getMembersList() {
        return membersList;
    }
    public void setMembersList(List<User> membersList) {
        this.membersList = membersList;
    }
    public int getCurrentUserId() {
        return currentUserId;
    }
    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }
    public int getAddUserId() {
        return addUserId;
    }
    public void setAddUserId(int addUserId) {
        this.addUserId = addUserId;
    }

    
    
}
