package com.project.appointment_schedule_management.Interface;

import java.time.LocalDate;
import java.time.LocalTime;

public interface WeeklyViewInter {
    
    int getUserId();
    int getScheduleId();
    String getTitle();
    String getDescription();
    LocalDate getStart();
    LocalTime getStart_time();
    LocalDate getEnd();
    LocalTime getEnd_time();
    String getStatus();
    Boolean getPrivacy();

}
