package com.project.appointment_schedule_management.model;

import java.io.Serializable;
import java.util.Arrays;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;



@Builder
@Data
@Entity
@Table ( name = "Image" )
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column( name = "id")
    private int imageId;
    // private String id;
    
    @Column ( name = "image_name")
    private String name;
    @Column ( name = "type")
    private String type;
    @Lob
    @Column ( name = "image_data", length = 1000)
    private byte[] imageData;

    
    
   
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getImageData() {
        return imageData;
    }
    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }
    public Image(String name, String type, byte[] imageData) {
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }
    public Image() {
    }
    @Override
    public String toString() {
        return "Image [imageId=" + imageId + ", name=" + name + ", type=" + type + ", imageData="
                + Arrays.toString(imageData) + "]";
    }
    public Image(Integer imageId, String name, String type, byte[] imageData) {
        this.imageId = imageId;
        this.name = name;
        this.type = type;
        this.imageData = imageData;
    }


    
    
    }



