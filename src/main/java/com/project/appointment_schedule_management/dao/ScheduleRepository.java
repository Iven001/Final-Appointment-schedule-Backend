package com.project.appointment_schedule_management.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.dto.AllScheduleMember;
import com.project.appointment_schedule_management.dto.SchduleDto;
import com.project.appointment_schedule_management.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {




    @Query("SELECT s FROM Schedule s WHERE title=:title")
    Schedule findByTitle(String title);

    // Schedule findByScheduleId (Integer id);

    @Query("SELECT new com.project.appointment_schedule_management.dto.AllScheduleMember(u.userId,s.id,s.start,s.end,s.start_time,s.end_time,s.title,s.description,s.privacy,s.place,s.status) "+
    "FROM User u,Schedule s,com.project.appointment_schedule_management.model.UserAndSchedule UserAndSchedule us " +
    "WHERE u.userId=us.usrId and s.id=us.schId")
    List<AllScheduleMember> getAllScheduleMember();
    
}
