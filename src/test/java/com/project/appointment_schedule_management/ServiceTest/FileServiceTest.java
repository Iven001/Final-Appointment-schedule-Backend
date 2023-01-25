package com.project.appointment_schedule_management.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayway.jsonpath.Option;
import com.project.appointment_schedule_management.Interface.ScheduleAttachment;
import com.project.appointment_schedule_management.dao.FileRepository;
import com.project.appointment_schedule_management.model.File;
import com.project.appointment_schedule_management.service.FileService;


@SpringBootTest
public class FileServiceTest {
    @Mock
    private FileRepository fileRepo;

    @InjectMocks
    private FileService fileService;

    private static File file;

    private static List<File> files;

    @BeforeAll
    public static void runBeforeAll(){

        file = new File();
        file.setFileId("H00123");
        file.setDocName("Doc");
        file.setDocType("DD");
        file.setData(null);
        file.setScheduleFileId(null);

        File file2 = new File();
        file2.setFileId("A0001");
        file2.setDocName("Abc");
        file2.setDocType("AA");
        file2.setData(null);
        file2.setScheduleFileId(null);

        files = new ArrayList<>();

        Collections.addAll(files, file, file2);
    }

    @Test
    public void saveFileTest(){
        when(this.fileRepo.save(file)).thenReturn(file);
        assertNotNull(this.fileRepo.save(file));
    }

    @Test
    public void saveAttTest(){
        // when(this.fileRepo.save(file));
        // assertNotNull(this.fileService.saveAtt(file));
    }

    @Test
    public void getFileTest(){
        
    }

    @Test
    public void getAllFilesTest(){
        List<File> excepted = fileRepo.findAll();
        Mockito.when(fileRepo.findAll()).thenReturn(excepted);
        List<File> actual = fileRepo.findAll();
        assertEquals(excepted, actual);
    }

    @Test
    public void getScheduleFilesTest(){
        List<ScheduleAttachment> excepted = fileRepo.getScheduleFiles(1);
        Mockito.when(fileRepo.getScheduleFiles(1)).thenReturn(excepted);
        List<ScheduleAttachment> actual = fileRepo.getScheduleFiles(1);
        assertEquals(excepted, actual);
    }

    
}
