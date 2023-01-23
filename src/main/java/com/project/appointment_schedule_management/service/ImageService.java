package com.project.appointment_schedule_management.service;

import java.io.IOException;
import java.util.ArrayList;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.appointment_schedule_management.dao.ImageRepository;
import com.project.appointment_schedule_management.model.Image;
import com.project.appointment_schedule_management.utils.ImageUtils;

@Service
public class ImageService {


   private final ImageRepository imgRepo;

   
    @Autowired
    public ImageService(ImageRepository imgRepo) {
    this.imgRepo = imgRepo;
}

    // public String uploadImage (MultipartFile file) throws IOException {

    //     Image img= imgRepo.save(Image.builder()
    //         .name(file.getOriginalFilename())
    //         .type(file.getContentType())
    //         .imageData(ImageUtils.compressImage(file.getBytes())).build());
    //     if (img != null ) {
    //         return "file uploaded successfully :"+ file.getOriginalFilename();
    //     }
    //     System.out.println("Null");
    //     return null;
    // }

    public byte[] downloadImage(String fileName) {
        ArrayList<Image> dbImageData = imgRepo.findByName(fileName);
    //   Image  dbImageData = imgRepo.findByfile(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get(0).getImageData());
        return images;
    }

    // public Image findById (int id) {
    //     return imgRepo.findById(id).get();
    // }

    public Image findById (Integer id) {
        return imgRepo.findById(id).get();
    }

    public ArrayList<Image> findByName (String filename){
        return imgRepo.findByName(filename);
    }



    public void deleteUser (int id) {
        imgRepo.deleteById(id);
    }

    public Stream<Image> getAllFiles() {
        return imgRepo.findAll().stream();
      }

      public String uploadImage (MultipartFile file) throws IOException {
    	System.out.println("Debuggiong ");

//        Image img= imgRepo.save(Image.s()
//            .name(file.getOriginalFilename())s
//            .type(file.getContentType())
//            .imageData(ImageUtils.compressImage(file.getBytes())).build());
    	Image img = imgRepo.save(new Image( file.getOriginalFilename() , file.getContentType() , ImageUtils.compressImage(file.getBytes())));
        if (img != null ) {
        	System.out.println("File uploaded successfully");
            return "file uploaded successfully :"+ file.getOriginalFilename();
            
        }
        System.out.println("Null");
        return null;
    }

   
}
