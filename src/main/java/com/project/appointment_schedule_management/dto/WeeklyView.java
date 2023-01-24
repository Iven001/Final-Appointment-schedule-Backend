package com.project.appointment_schedule_management.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class WeeklyView {

    private int userId;
    private int scheduleId;
    private String description;
    private LocalDate start;
    private LocalTime start_time;
    private LocalDate end;
    private LocalTime end_time;
    private String status;
    private Boolean privacy;
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getStart() {
        return start;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }
    public LocalTime getStart_time() {
        return start_time;
    }
    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }
    public LocalDate getEnd() {
        return end;
    }
    public void setEnd(LocalDate end) {
        this.end = end;
    }
    public LocalTime getEnd_time() {
        return end_time;
    }
    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Boolean getPrivacy() {
        return privacy;
    }
    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    
    
}
