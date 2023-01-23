package com.project.appointment_schedule_management.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;
import net.bytebuddy.asm.Advice.Local;

@Data
public class AllScheduleMember implements Serializable {

    private int userId;
    private int id;
    private LocalDate start;
    private LocalDate end;
    private LocalTime start_time;
    private LocalTime end_time;
    private String title;
    private String description;
    private Boolean privacy;
    private String place;
    private String status;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getStart() {
        return start;
    }
    public void setStart(LocalDate start) {
        this.start = start;
    }
    public LocalDate getEnd() {
        return end;
    }
    public void setEnd(LocalDate end) {
        this.end = end;
    }
    public LocalTime getStart_time() {
        return start_time;
    }
    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }
    public LocalTime getEnd_time() {
        return end_time;
    }
    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getPrivacy() {
        return privacy;
    }
    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public AllScheduleMember(int userId, int id, LocalDate start, LocalDate end, LocalTime start_time,
            LocalTime end_time, String title, String description, Boolean privacy, String place, String status) {
        this.userId = userId;
        this.id = id;
        this.start = start;
        this.end = end;
        this.start_time = start_time;
        this.end_time = end_time;
        this.title = title;
        this.description = description;
        this.privacy = privacy;
        this.place = place;
        this.status = status;
    }

    
  
    
}
