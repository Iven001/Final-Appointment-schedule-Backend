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

    // @PostMapping
    // public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile
    // file, @RequestParam("id") Integer id)
    // throws IOException {

    // try {
    // String uploadImage = imgService.uploadImage(file);
    // // if (uploadImage.isEmpty()) {
    // // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(uploadImage);
    // // }else {
    // int userId = id;

    // User u = userService.findById(userId);
    // String imgName = file.getOriginalFilename();
    // System.out.println(imgName);
    // String test = imgName;
    // ArrayList<Image> i = imgService.findByName(imgName);
    // for (int a = 0; a <= i.size() - 1; a++) {
    // Image imgToSet = i.get(a);
    // u.setImgId(imgToSet.getImageId());
    // }

    // userService.saveUser(u);

    // return ResponseEntity.status(HttpStatus.CREATED).body(uploadImage);

    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    // }
    // }

    @GetMapping("/hello/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {

        byte[] imageData = imgService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    // @GetMapping("/getImage/{id}")
    // public ResponseEntity<?> getUserImages (@PathVariable Integer id) {

    // int userId=id;
    // User u = userService.findById(userId);
    // Integer imageId = u.getImgId();
    // Image img = imgService.findById(imageId);
    // // String fileName = img.getName();

    // byte[] imageData = imgService.downloadImage(img.getName());
    // return ResponseEntity.status(HttpStatus.OK)
    // .contentType(MediaType.valueOf("image/png"))
    // .body(imageData);
    // }

    // @GetMapping("/userImage/{id}")
    // public ResponseEntity<?> userImage (@PathVariable Integer id) {
    // Image i = imgService.findById(id);

    // String fileName =i.getName();
    // byte[] imageData = imgService.downloadImage(fileName);
    // return ResponseEntity.status(HttpStatus.OK)
    // .contentType(MediaType.valueOf("image/png"))
    // .body(imageData);
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> deleteImage (@PathVariable int
    // id,@RequestParam("id") Integer useID) {

    // try {
    // imgService.deleteUser(id);
    // return ResponseEntity.status(HttpStatus.OK).body("Image deleted
    // successfully!");
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    // }
    // }

    // @PostMapping("/update/{id}")
    // public ResponseEntity<?> updateImage(@PathVariable("id") int
    // id,@RequestParam("image")MultipartFile file) throws IOException {
    // Optional<Image> img= imgRepo.findById(id);

    // try {
    // if (img.isPresent()) {
    // imgService.deleteUser(id);
    // String uploadImage = imgService.uploadImage(file);
    // return ResponseEntity.status(HttpStatus.CREATED).body(uploadImage);
    // }
    // }catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    // }
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    // }

    @PostMapping("/dejavu")
    public ResponseEntity<?> uploadImage(@RequestPart MultipartFile file, @RequestParam("userId") int id)
            throws IOException {

        try {
            System.out.println("From Angular **");
            System.out.println("UploadImage");
            String uploadImage = imgService.uploadImage(file);
            System.out.println("upload Image message : " + uploadImage);
            // if (uploadImage.isEmpty()) {
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(uploadImage);
            // }else {
            // int userId = Integer.parseInt(id);

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
