package com.project.appointment_schedule_management.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import com.project.appointment_schedule_management.model.File;
import com.project.appointment_schedule_management.model.User;

import lombok.Data;

//@Data
public class SchduleDto {

    private int Schid;
    private LocalDate start;
    private LocalDate end;
    private LocalTime start_time;
    private LocalTime end_time;
    private String title;
    private String description;
    private Boolean privacy;
    private String place;
    private String status;
    private int createUser;
    private LocalDate createDate;
    private int updateUser;
    private LocalDate upDateTime;
    private int deleteUser;
    private int ownerId;
    private Boolean isDelete;
    private List<User> membersList;

    // private List<File> schduleFile;
    // private List<User> members;
    public int getSchid() {
        return Schid;
    }

    public void setSchid(int schid) {
        Schid = schid;
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

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(int updateUser) {
        this.updateUser = updateUser;
    }

    public LocalDate getUpDateTime() {
        return upDateTime;
    }

    public void setUpDateTime(LocalDate upDateTime) {
        this.upDateTime = upDateTime;
    }

    public int getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(int deleteUser) {
        this.deleteUser = deleteUser;
    }

    public List<User> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<User> membersList) {
        this.membersList = membersList;
    }

    public SchduleDto() {
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

  
   

}
