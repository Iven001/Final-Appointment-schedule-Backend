package com.project.appointment_schedule_management.dto;

public class ResponseData {

    private String fileId;
    private String name;
	private String url;
	private String type;
    private long size;

    


	public ResponseData() {
    }
    public ResponseData(String fileId, String name, String url, String type, long size) {
        this.fileId = fileId;
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public long getSize() {
        return size;
    }
    public void setSize(long size) {
        this.size = size;
    }
    public String getFileId() {
        return fileId;
    }
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
    
    
    

}