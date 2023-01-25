package com.project.appointment_schedule_management.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.Interface.InterSchedule;
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

    // Jasper Report  getDailyReport(int userId, LocalDate currentDate)
    // @Query(value = "Select s.start_time,s.end_time,s.title,s.description,s.privacy,s.place "
    // + "From appointment.schedule s , appointment.user u , appointment.users_schedule us "
    // + "Where s.schedule_id=us.schedule_id and u.user_id=us.user_id and u.user_id=:userId and s.start_date=:start ",nativeQuery = true)
    // List<InterSchedule> dailyReport(int userId, LocalDate start);
    
}
