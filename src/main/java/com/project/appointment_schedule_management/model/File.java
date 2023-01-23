package com.project.appointment_schedule_management.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table ( name = "attachment" )
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String fileId;
	
    @Column ( name = "document_name")
	private String docName;

    @Column ( name = "document_type")
	private String docType;

    @Column ( name = "file_size")
    private long size;
	
	@Lob
    @Column ( name = "data")
	private byte[] data;


    @ManyToOne
    @JoinColumn(name="schedule_file_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Schedule scheduleFileId;



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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public File() {
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Schedule getScheduleFileId() {
        return scheduleFileId;
    }

    public void setScheduleFileId(Schedule scheduleFileId) {
        this.scheduleFileId = scheduleFileId;
    }

    public File(String docName, String docType, byte[] data, long size, Schedule scheduleFileId) {
        this.docName = docName;
        this.docType = docType;
        this.data = data;
        this.size = size;
        this.scheduleFileId = scheduleFileId;
    }
    
   
    // public static long getSerialversionuid() {
    //     return serialVersionUID;
    // }

   
    

    
    // @Override
    // public String toString() {
    //     return "File [fileId=" + fileId + ", docName=" + docName + ", docType=" + docType + ", data="
    //             + Arrays.toString(data) + ", schduleId=" + schduleId + ", schdule=" + schdule + "]";
    // }
    

    
}
