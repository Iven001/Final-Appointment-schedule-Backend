package com.project.appointment_schedule_management.Interface;

import java.time.LocalDate;
import java.time.LocalTime;

public interface UserSchedule {

    int getUserId();

    String getUname();

    int getId();

    LocalDate getStart();

    LocalDate getEnd();

    LocalTime getStart_time();

    LocalTime getEnd_time();

    String getStatus();

    Boolean getPrivacy();

    String getPlace();

    String getDescription();

    String getTitle();

    String getTeamName();

}
