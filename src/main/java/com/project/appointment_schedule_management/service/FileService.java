package com.project.appointment_schedule_management.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.appointment_schedule_management.Interface.ScheduleAttachment;
import com.project.appointment_schedule_management.dao.FileRepository;
import com.project.appointment_schedule_management.model.File;




@Service
public class FileService {

    private final FileRepository fileRepository;

        @Autowired
        public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

        public File saveAtt (File file) throws IOException {
            return fileRepository.save(file);
        }

        public File saveFile (MultipartFile file) throws IOException {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            File fileData = new File(fileName, file.getContentType(), file.getBytes(), 0, null);

            return fileRepository.save(fileData);
        }

        public File getFile (String id) {
            return fileRepository.findById(id).get();
        }

        public Stream<File> getAllFiles () {
            return fileRepository.findAll().stream();
        }

        public List<ScheduleAttachment> getScheduleFiles (int scheduleId) {
            List<ScheduleAttachment> list = fileRepository.getScheduleFiles(scheduleId);
            return list;
        }

        public void deleteFile (String id) {
            fileRepository.deleteById(id);
        }


}

    

  
