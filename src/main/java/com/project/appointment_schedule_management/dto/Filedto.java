package com.project.appointment_schedule_management.dto;

import com.project.appointment_schedule_management.model.Schedule;

import lombok.Data;

@Data
public class Filedto {

    private String fileId;
    private String docName;
    private String docType;
    private byte[] data;
     private Schedule scheduleFileId;
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    public String getDocName() {
        return docName;
    }
    public void setDocName(String docName) {
        this.docName = docName;
    }
    public String getDocType() {
        return docType;
    }
    public void setDocType(String docType) {
        this.docType = docType;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    public Schedule getScheduleFileId() {
        return scheduleFileId;
    }
    public void setScheduleFileId(Schedule scheduleFileId) {
        this.scheduleFileId = scheduleFileId;
    }


     
    
}
