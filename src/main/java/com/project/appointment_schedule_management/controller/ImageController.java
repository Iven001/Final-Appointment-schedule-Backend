package com.project.appointment_schedule_management.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.appointment_schedule_management.dao.ImageRepository;
import com.project.appointment_schedule_management.dto.ProfileImage;
import com.project.appointment_schedule_management.model.Image;
import com.project.appointment_schedule_management.model.User;
import com.project.appointment_schedule_management.service.ImageService;
import com.project.appointment_schedule_management.service.UserService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageRepository imgRepo;
    private final UserService userService;
    private final ImageService imgService;

    public ImageController(ImageRepository imgRepo, UserService userService, ImageService imgService) {
        this.imgRepo = imgRepo;
        this.userService = userService;
        this.imgService = imgService;
    }


    @GetMapping("/hello/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {

        byte[] imageData = imgService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }


    @PostMapping("/dejavu")
    public ResponseEntity<?> uploadImage(@RequestPart MultipartFile file, @RequestParam("userId") int id)
            throws IOException {

        try {
            System.out.println("From Angular **");
            System.out.println("UploadImage");
            String uploadImage = imgService.uploadImage(file);
            System.out.println("upload Image message : " + uploadImage);
          

            User u = userService.findById(id);
            String imgName = file.getOriginalFilename();
            System.out.println(imgName);
            String test = imgName;
            ArrayList<Image> i = imgService.findByName(imgName);
            for (int a = 0; a <= i.size() - 1; a++) {
                Image imgToSet = i.get(a);
                u.setImgId(imgToSet.getImageId());
            }

            userService.saveUser(u);

            return ResponseEntity.status(HttpStatus.CREATED).body(uploadImage);

        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // By Min Phyoe Min Thu
    // I add ProfileImage DTO
    @GetMapping("/getImage")
    public ResponseEntity<?> getUserImage(@RequestParam Integer id) {

        Integer userId = id;
        User u = userService.findById(userId);
        Integer imageId = u.getImgId();
        Image img = imgService.findById(imageId);
        // String fileName = img.getName();

        byte[] imageData = imgService.downloadImage(img.getName());
        // byte[] decodedImage=ImageUtils.decompressImage(imageData);
        System.out.println("Decoded Image : " + imageData);

        ProfileImage profileImage = new ProfileImage();
        profileImage.setUserImageData(imageData);

        try {
            if (imageData == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image Data is null.");
            }
            return ResponseEntity.status(HttpStatus.OK).body(profileImage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
