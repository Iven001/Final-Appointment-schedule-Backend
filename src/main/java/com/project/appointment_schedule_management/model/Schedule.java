package com.project.appointment_schedule_management.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "schedule")
public class Schedule implements Serializable {
    // public class Schedule {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_id")
    private int id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "start_date", nullable = false)
    private LocalDate start;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "end_date", nullable = false)
    private LocalDate end;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "start_time", nullable = false)
    private LocalTime start_time;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @Column(name = "end_time", nullable = false)
    private LocalTime end_time;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "privacy")
    private Boolean privacy;

    @Column(name = "place")
    private String place;

    @Column(name = "create_userid")
    private int createUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "update_userid")
    private int updateUser;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "update_time")
    private LocalDate updatetime;

    @Column(name = "delete_user")
    private int deleteUser;

    @Column(name = "status")
    private String status;

    @Column(name = "owner")
    private int ownerId;

  //  @Column(name = "delete_schedule", columnDefinition = "boolean default false")
    @Column(name = "is_delete")
    private Boolean isDelete;

    @OneToMany(mappedBy = "scheduleFileId")
    private List<File> files;

    @JoinColumn
    @ManyToMany()
    @JoinTable(name = "users_schedule", joinColumns = { @JoinColumn(name = "schedule_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_id") })
    @JsonIgnore
    private List<User> members;

    public Schedule() {
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

    public LocalDate getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDate updatetime) {
        this.updatetime = updatetime;
    }

    public int getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(int deleteUser) {
        this.deleteUser = deleteUser;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
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

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Schedule(LocalDate start, LocalDate end, LocalTime start_time, LocalTime end_time, String title,
            String description, Boolean privacy, String place, Integer createUser, LocalDate createDate, String status,
            int ownerId) {
        this.start = start;
        this.end = end;
        this.start_time = start_time;
        this.end_time = end_time;
        this.title = title;
        this.description = description;
        this.privacy = privacy;
        this.place = place;
        this.createUser = createUser;
        this.createDate = createDate;
        this.status = status;
        this.ownerId = ownerId;
    }

    public Schedule(LocalDate start, LocalDate end, LocalTime start_time, LocalTime end_time, String title,
            String description, Boolean privacy, String place, int createUser, LocalDate createDate, int updateUser,
            LocalDate updatetime, int deleteUser, String status, int ownerId, Boolean isDelete, List<File> files,
            List<User> members) {
        this.start = start;
        this.end = end;
        this.start_time = start_time;
        this.end_time = end_time;
        this.title = title;
        this.description = description;
        this.privacy = privacy;
        this.place = place;
        this.createUser = createUser;
        this.createDate = createDate;
        this.updateUser = updateUser;
        this.updatetime = updatetime;
        this.deleteUser = deleteUser;
        this.status = status;
        this.ownerId = ownerId;
        this.isDelete = isDelete;
        this.files = files;
        this.members = members;
    }


    

   
    
}
