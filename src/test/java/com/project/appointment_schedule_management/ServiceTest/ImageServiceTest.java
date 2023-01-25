package com.project.appointment_schedule_management.serviceTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.appointment_schedule_management.dao.ImageRepository;
import com.project.appointment_schedule_management.model.Employee;
import com.project.appointment_schedule_management.model.Image;
import com.project.appointment_schedule_management.service.ImageService;

@SpringBootTest
public class ImageServiceTest {
    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageService imageService;

    private static Image image;

    private static List<Image> images;

    @BeforeAll
    public static void runBeforeAll(){

        image = new Image();
        image.setImageId(1);
        image.setName("h.jpg");
        image.setType("jpg");
        image.setImageData(null);

        Image image2 = new Image();
        image2.setImageId(2);
        image2.setName("gg");
        image2.setType(".png");
        image2.setImageData(null);

        images = new ArrayList<>();

        Collections.addAll(images, image, image2);
    }

    @Test
    private void findByIdTest(){
        // when(this.imageRepository.findById(1)).thenReturn(Optional.of(image));
        // assertNotNull(this.imageService.findById(1));
        // verify(this.imageRepository, times(1)).findById(1);

        // When(this.imageRepository.findById(1)).thenReturn(Optional.of(image));
        // assertNotNull(this.imageService.findById(1));

    }

    @Test
    private void findByNameTest(){
        when(this.imageRepository.findByName("NN"));
        assertNotNull(this.imageService.findByName("NN"));

        // List<Image> excepted = imageRepository.findByName("gg");
        // Mockito.when(imageRepository.findByName("gg")).thenReturn(excepted);
    }






}
