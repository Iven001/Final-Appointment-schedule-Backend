package com.project.appointment_schedule_management.dto;

import java.io.Serializable;


import com.project.appointment_schedule_management.model.Schedule;

public class UserRequestDto implements Serializable {

    private int requestId;
    private String userRequest;
    private int userRequestId;
    private Schedule scheduleRequestId;
    public int getRequestId() {
        return requestId;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    public String getUserRequest() {
        return userRequest;
    }
    public void setUserRequest(String userRequest) {
        this.userRequest = userRequest;
    }
    public int getUserRequestId() {
        return userRequestId;
    }
    public void setUserRequestId(int userRequestId) {
        this.userRequestId = userRequestId;
    }
    public Schedule getScheduleRequestId() {
        return scheduleRequestId;
    }
    public void setScheduleRequestId(Schedule scheduleRequestId) {
        this.scheduleRequestId = scheduleRequestId;
    }
    public UserRequestDto(int requestId, String userRequest, int userRequestId, Schedule scheduleRequestId) {
        this.requestId = requestId;
        this.userRequest = userRequest;
        this.userRequestId = userRequestId;
        this.scheduleRequestId = scheduleRequestId;
    }
    @Override
    public String toString() {
        return "UserRequestDto [requestId=" + requestId + ", userRequest=" + userRequest + ", userRequestId="
                + userRequestId + ", scheduleRequestId=" + scheduleRequestId + "]";
    }

    
    

    
}
