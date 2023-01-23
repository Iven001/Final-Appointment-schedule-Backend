package com.project.appointment_schedule_management.model;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="Request")
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="request_id")
    private int requestId;
    @Column(name = "user_request")
    private String userRequest;
    @Column(name = "user_request_id")
    private int userRequestId;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "schedule_request_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
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

    public UserRequest(int requestId, String userRequest, int userRequestId, Schedule scheduleRequestId) {
        this.requestId = requestId;
        this.userRequest = userRequest;
        this.userRequestId = userRequestId;
        this.scheduleRequestId = scheduleRequestId;
    }

    

    public UserRequest() {
    }

    @Override
    public String toString() {
        return "UserRequest [requestId=" + requestId + ", userRequest=" + userRequest + ", userRequestId="
                + userRequestId + ", scheduleRequestId=" + scheduleRequestId + "]";
    }

    
    

    
}
