package com.project.appointment_schedule_management.service;

import java.util.List;

import com.project.appointment_schedule_management.Interface.InterSchedule;
import com.project.appointment_schedule_management.dto.AllScheduleMember;
import com.project.appointment_schedule_management.dto.SchduleDto;
import com.project.appointment_schedule_management.model.Schedule;

public interface ScheduleService {
    
    List<Schedule> getAllSchedules ();

    Schedule saveSchedule (SchduleDto schedule);

    //Schedule updateSchedule (SchduleDto schduleDto);

    Schedule getScheduleById (Integer i);

    void deleteSchedule (int id);

   Schedule findByTitle (String title);

   Schedule findByScheduleId (Integer id);

   List<AllScheduleMember> getAllScheduleMember();

   Schedule save(Schedule schedule);
 

}
