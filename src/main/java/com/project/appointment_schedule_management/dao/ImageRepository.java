package com.project.appointment_schedule_management.dao;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    
    ArrayList<Image> findByName (String fileName);

     Optional<Image> findById(Integer id);

    

    // void deleteById (Integer id);

}
