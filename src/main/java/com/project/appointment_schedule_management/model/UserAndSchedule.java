package com.project.appointment_schedule_management.model;

import java.io.Serializable;

public class UserAndSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    private int schId;
    private int usrId;
    public int getSchId() {
        return schId;
    }
    public void setSchId(int schId) {
        this.schId = schId;
    }
    public int getUsrId() {
        return usrId;
    }
    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }



    
    
}
