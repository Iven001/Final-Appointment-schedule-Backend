package com.project.appointment_schedule_management.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.appointment_schedule_management.Interface.InterSchedule;
import com.project.appointment_schedule_management.dao.ScheduleRepository;
import com.project.appointment_schedule_management.dto.AllScheduleMember;
import com.project.appointment_schedule_management.dto.SchduleDto;
import com.project.appointment_schedule_management.model.Schedule;
import com.project.appointment_schedule_management.model.User;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository schRepo;
    private final UserService userService;

    
     @Autowired
    public ScheduleServiceImpl(ScheduleRepository schRepo, UserService userService) {
        this.schRepo = schRepo;
        this.userService = userService;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        List<Schedule> list = schRepo.findAll();
        return list;
    }

    @Override
    public void deleteSchedule(int id) {
        schRepo.deleteById(id);
    }

    @Override
    public Schedule saveSchedule(SchduleDto schedule) {

        LocalDate now = LocalDate.now();

        User u = userService.findById(schedule.getCreateUser());

        List<User> membersList = schedule.getMembersList();
        membersList.add(u);

        Schedule newSch = new Schedule();
        newSch.setStart(schedule.getStart());
        newSch.setEnd(schedule.getEnd());
        newSch.setStart_time(schedule.getStart_time());
        newSch.setEnd_time(schedule.getEnd_time());
        newSch.setTitle(schedule.getTitle());
        newSch.setDescription(schedule.getDescription());
        newSch.setPrivacy(schedule.getPrivacy());
        newSch.setPlace(schedule.getPlace());
        newSch.setStatus("ongoing");
        newSch.setCreateUser(schedule.getCreateUser());
        newSch.setCreateDate(now);
        newSch.setOwnerId(schedule.getCreateUser());
        newSch.setIsDelete(false);
        newSch.setMembers(membersList);

        return schRepo.save(newSch);
    }

    @Override
    public Schedule findByTitle(String title) {

        return schRepo.findByTitle(title);
    }

    @Override
    public Schedule getScheduleById(Integer i) {

        return schRepo.findById(i).get();
    }

    @Override
    public Schedule findByScheduleId(Integer id) {

        return schRepo.findById(id).get();
    }

    @Override
    public List<AllScheduleMember> getAllScheduleMember() {
        List<AllScheduleMember> list = (List<AllScheduleMember>) schRepo.getAllScheduleMember();
        return list;
    }

    @Override
    public Schedule save(Schedule schedule) {

        return schRepo.save(schedule);
    }

    // @Override
    // public Schedule updateSchedule(SchduleDto schduleDto) {

    //     LocalDate now = LocalDate.now();

    //     Schedule newSch = new Schedule();
    //     newSch.setUpdateUser(schduleDto.getUpdateUser());
    //     newSch.setUpdatetime(now);
    //     newSch.setMembers(newSch.getMembers());

        
    //     return schRepo.save(newSch);
    // }

}
