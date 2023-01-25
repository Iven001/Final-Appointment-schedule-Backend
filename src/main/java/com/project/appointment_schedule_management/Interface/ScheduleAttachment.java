package com.project.appointment_schedule_management.Interface;

public interface ScheduleAttachment {

    int getId();

    String getFileId();

    byte[] getData();

    String getDocName();

    String getDocType();

    long getSize();

}
