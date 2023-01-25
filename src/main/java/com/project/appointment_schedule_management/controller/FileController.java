package com.project.appointment_schedule_management.controller;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.appointment_schedule_management.model.File;
import com.project.appointment_schedule_management.model.Schedule;
import com.project.appointment_schedule_management.Interface.ScheduleAttachment;
import com.project.appointment_schedule_management.dao.FileRepository;
import com.project.appointment_schedule_management.dao.ScheduleRepository;
import com.project.appointment_schedule_management.dto.Filedto;
import com.project.appointment_schedule_management.dto.ResponseData;
import com.project.appointment_schedule_management.dto.ResponseMessage;
import com.project.appointment_schedule_management.service.FileService;
import com.project.appointment_schedule_management.service.ScheduleService;



@CrossOrigin(origins = "http://localhost:4200" )
@RestController
@RequestMapping("/file")
public class FileController {


    private final FileRepository fileRep;
    private final FileService fileService;
    private final ScheduleService schService;
    private final ScheduleRepository schRepo;

    @Autowired
    public FileController(FileRepository fileRep, FileService fileService, ScheduleService schService,
            ScheduleRepository schRepo) {
        this.fileRep = fileRep;
        this.fileService = fileService;
        this.schService = schService;
        this.schRepo = schRepo;
    }
    
    

    @PostMapping
    public ResponseEntity<ResponseMessage> uploadFile (@RequestParam("file") MultipartFile file)
    throws IOException {
        String message = "";

        try{
            fileService.saveFile(file);
            message = "Uploaded file successfully: "+ file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload file: "+file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

     

    @PostMapping("/multipartfile")
    public ResponseEntity<ResponseMessage> uploadMultiPartFile (@RequestParam("file") MultipartFile[] file)
    throws IOException {
        String message = "";

        try{
            for (MultipartFile files : file){
                fileService.saveFile(files);
               
            } 
               // message = "Uploaded file successfully: "+ files.getOriginalFilename();
               message = "Uploaded file successfully: ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
           // message = "Could not upload file: "+files.getOriginalFilename() + "!";
           message = "Could not upload file: ";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/all")
    public ResponseEntity <?> getListFiles (@RequestParam String fileId) {
        
       try {

        List<ResponseData> files = fileService.getAllFiles().map(fileData -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/file/all")
                            .path(fileData.getFileId())
                            .toUriString();

                    return new ResponseData(
                        fileData.getFileId(),
                       // fileData.getScheduleFileId(),
                        fileData.getDocName(),
                        fileDownloadUri,
                        fileData.getDocType(),
                        fileData.getData().length);
        }).collect(Collectors.toList());
        ResponseData data = new ResponseData();
        for (int i =0; i < files.size(); i++) {
           if(files.get(i).getFileId().equals(fileId)){
            data.setFileId(files.get(i).getFileId());
            data.setName(files.get(i).getName());
            data.setUrl(files.get(i).getUrl());
            data.setType(files.get(i).getType());
            data.setSize(files.get(i).getSize());
           }
        }
        return ResponseEntity.status(HttpStatus.OK).body(data);
        
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something was wrong!");
       }
        
    }


    @Async
    @PostMapping("/up")
    public ResponseEntity<?> upLoadFile (@RequestParam("file") MultipartFile[] file,@RequestParam String title )throws IOException{

        String t = title;
        t = t.substring(1, t.length() - 1);

        System.out.println("Execute method asynchronously - " 
      + Thread.currentThread().getName());

        try {
            Thread.sleep(6000);
            Schedule sch =schService.findByTitle(t);

            int j =  ((Schedule) sch).getId();


             for (MultipartFile files : file) {
                 File att = new File();
                 att.setDocName(files.getOriginalFilename());
             att.setData(files.getBytes());
             att.setDocType(files.getContentType());
             att.setSize(files.getSize());
            att.setScheduleFileId(sch);
             fileService.saveAtt(att);
             }
        return ResponseEntity.status(HttpStatus.OK).body(file);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something was wrong!");
        }

    }

    @GetMapping("/getAllScheduleFiles")
    public ResponseEntity<?> getAttachmetns (int scheduleId)throws IOException{

        try {
            List<ScheduleAttachment> list = fileService.getScheduleFiles(scheduleId);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @DeleteMapping("/deleteFile")
    public ResponseEntity<?> deleteFile (@RequestBody Filedto dto) {

        try {
            Schedule schedule = schService.findByScheduleId(dto.getScheduleId());

            if (schedule!=null && schedule.getOwnerId()==dto.getCuurentUserId() ) {
                fileService.deleteFile(dto.getFileId());
                return ResponseEntity.status(HttpStatus.OK).body(schedule);
            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
            }

            
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);        }
    }
}
