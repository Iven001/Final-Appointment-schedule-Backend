package com.project.appointment_schedule_management.Interface;

import java.time.LocalDate;
import java.time.LocalTime;

public interface InterSchedule {

    int getUserId();

    String getUname();

    int getScheduleId();

    LocalDate getStart();

    LocalDate getEnd();

    LocalTime getStart_time();

    LocalTime getEnd_time();

    String getDescription();

    String getPlace();

    Boolean getPrivacy();

    String getStatus();

    String getTitle();

    Boolean getIsDelete();

    int getOwnerId();

}
