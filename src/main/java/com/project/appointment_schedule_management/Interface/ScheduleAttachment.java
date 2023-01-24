package com.project.appointment_schedule_management.Interface;

public interface ScheduleAttachment {

    int getId();

    String getFileId();

    Byte[] getData();

    String getDocName();

    String getDocType();

    long getSize();

}
