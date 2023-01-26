package com.project.appointment_schedule_management.controller;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.appointment_schedule_management.Interface.InterSchedule;
import com.project.appointment_schedule_management.dao.FileRepository;
import com.project.appointment_schedule_management.dao.ScheduleRepository;
import com.project.appointment_schedule_management.dao.UserRepository;
import com.project.appointment_schedule_management.dto.AddMembers;
import com.project.appointment_schedule_management.dto.AllScheduleMember;
import com.project.appointment_schedule_management.dto.ChangeOwnerDto;
import com.project.appointment_schedule_management.dto.SchduleDto;
import com.project.appointment_schedule_management.model.Schedule;
import com.project.appointment_schedule_management.model.User;
import com.project.appointment_schedule_management.service.FileService;
import com.project.appointment_schedule_management.service.ScheduleService;
import com.project.appointment_schedule_management.service.UserService;
import com.project.appointment_schedule_management.utils.DeleteScheduleMail;
import com.project.appointment_schedule_management.utils.InviteMail;
import com.project.appointment_schedule_management.utils.RemoveFromMeetingRequestMail;
import com.project.appointment_schedule_management.utils.RemoveUserMail;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private InviteMail emailTask;

    @Autowired
    private DeleteScheduleMail deleteMail;

    @Autowired
    private RemoveUserMail removeUserMail;

    @Autowired
    private RemoveFromMeetingRequestMail requestMail;

    private final ScheduleService schService;
    private final ScheduleRepository schRepo;
    private final UserService userService;
    private final UserRepository userRepo;
    private final FileRepository fileRepo;
    private final FileService fileService;

    public ScheduleController(ScheduleService schService, ScheduleRepository schRepo, UserService userService,
            UserRepository userRepo, FileRepository fileRepo, FileService fileService) {
        this.schService = schService;
        this.schRepo = schRepo;
        this.userService = userService;
        this.userRepo = userRepo;
        this.fileRepo = fileRepo;
        this.fileService = fileService;
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<?> createRegister(@RequestBody SchduleDto sch)
            throws UnsupportedEncodingException, MessagingException {

               
                    schService.saveSchedule(sch);
        try {
            // schService.saveSchedule(sch);
            List<User> members = sch.getMembersList();
            User createUser = userService.findById(sch.getCreateUser());
            String createUserName = createUser.getUname();
            for (User u : members) {
                if(u.getUserId() != createUser.getUserId()){
                    emailTask.sendEmail(u.getMail(), sch , createUserName);
                }
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(sch);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/getUserSchedule/{id}")
    public ResponseEntity<?> getDateAndTime(@PathVariable("id") Integer id) {

        try {
            Optional<Schedule> dateTime = schRepo.findById(id);
            if (dateTime.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(dateTime.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/allSchedules")
    public ResponseEntity<?> getAllUsers() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(schService.getAllSchedules());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/getAllScheduleMembers")
    public ResponseEntity<?> getAllScheduleMembers() {

        try {
            List<AllScheduleMember> list = schService.getAllScheduleMember();
            return ResponseEntity.status(HttpStatus.OK).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/title")
    public ResponseEntity<?> getTitle(@RequestParam String title) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(schService.findByTitle(title));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PutMapping("/removeUser")
    public ResponseEntity<?> deleteUserFromSchedule(@RequestBody ChangeOwnerDto dto) throws UnsupportedEncodingException, MessagingException {
        LocalDate upDate = LocalDate.now();
        Schedule schedule = schService.findByScheduleId(dto.getScheduleId());
        User u = userService.findById(dto.getUserId());
        List<User> users = schedule.getMembers();
        if (schedule != null && schedule.getOwnerId() == dto.getCurrentUserId()) {
            for (Iterator<User> itr = users.iterator(); itr.hasNext();) {
                User user = itr.next();
                if (user.getUserId() == dto.getUserId() && user.getUserId() != schedule.getOwnerId()) {
                    itr.remove();
                    removeUserMail.sendEmail(u.getMail(), schedule.getTitle());
                }
            }
            schedule.setUpdatetime(upDate);
            schedule.setUpdateUser(dto.getCurrentUserId());
            schedule.setMembers(users);
            schService.save(schedule);
            
          
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check Ur User Informantion!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(schedule);
    }

    @PutMapping("/deleteSchedule")
    public ResponseEntity<?> deleteSchedule(@RequestBody ChangeOwnerDto dto) {

        LocalDate lt = LocalDate.now();
        try {
            Schedule sch = schService.findByScheduleId(dto.getScheduleId());
            List<User> members = sch.getMembers();
            String schTitle = sch.getTitle();

            if (sch != null) {
                if (sch.getCreateUser() == dto.getCurrentUserId() || sch.getOwnerId() == dto.getOwnerId()) {
                    sch.setDeleteUser(dto.getCurrentUserId());
                    sch.setIsDelete(dto.getIsDelete());
                    sch.setStatus("isDelete");
                    schService.save(sch);

                    for (User u : members){
                        deleteMail.sendEmail(u.getMail(), schTitle);
                    }
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check ur User Information");
                }

                // schService.deleteSchedule(scheduleId);
            }
            return ResponseEntity.status(HttpStatus.OK).body(sch);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PutMapping("/changeOwner")
    public ResponseEntity<?> changeOwnerFromSchedule(@RequestBody ChangeOwnerDto dto) {

        LocalDate updateDate = LocalDate.now();

        try {
            Schedule sch = schService.findByScheduleId(dto.getScheduleId());
            List<User> memberlist = sch.getMembers();

            if (sch != null && sch.getCreateUser() == dto.getCurrentUserId()) {

                for (User u : memberlist) {
                    if (u.getUserId() == dto.getUserId()) {
                        // sch.setId(dto.getScheduleId());
                        sch.setUpdateUser(dto.getCurrentUserId());
                        sch.setUpdatetime(updateDate);
                        sch.setOwnerId(dto.getUserId());

                    }
                }
                schService.save(sch);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check Ur userDetails");
            }
            return ResponseEntity.status(HttpStatus.OK).body(sch);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    @PutMapping("/finishSchedule")
    public ResponseEntity<?> finishSchedule (@RequestBody ChangeOwnerDto dto) {

        LocalDate updateDate = LocalDate.now();

        try {
            Schedule sch = schService.findByScheduleId(dto.getScheduleId());

            if (sch!=null && sch.getOwnerId()==dto.getCurrentUserId()) {
                    sch.setUpdatetime(updateDate);
                    sch.setUpdateUser(dto.getCurrentUserId());
                    sch.setStatus("finished");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check Ur userDetails");
            }
            schService.save(sch);
            return ResponseEntity.status(HttpStatus.OK).body(sch);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }



    @PutMapping("/addMembers")
    public ResponseEntity<?> addUserFromSchedule(@RequestBody AddMembers dto) {

        LocalDate now = LocalDate.now();

        try {

            Schedule schedule = schService.findByScheduleId(dto.getScheduleId());

       
            User u = userService.getUserById(dto.getAddUserId());

            // List<User> users = dto.getMembersList();
            List<User> userList = dto.getMembersList();
            userList.add(u);
            if (schedule.getOwnerId() == dto.getCurrentUserId()) {
                schedule.setUpdateUser(dto.getCurrentUserId());
                schedule.setUpdatetime(now);
                schedule.setMembers(userList);
                schService.save(schedule);
            }
            //schService.save(schedule);
            return ResponseEntity.status(HttpStatus.OK).body(schedule);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PutMapping("/request")
    public ResponseEntity<?> requestToQuit (@RequestBody ChangeOwnerDto dto) {

        try {
            Schedule schedule = schService.findByScheduleId(dto.getScheduleId());
            if (schedule!=null) {
                User organizer = userService.getUserById(dto.getOwnerId());
                User requestUser = userService.getUserById(dto.getUserId());
                requestMail.sendEmail(organizer.getMail(),dto.getStatus(),schedule.getTitle(),requestUser.getUname());
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }

            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


    // @GetMapping("/dailyReport")
    // public ResponseEntity<?> DailyReport (@RequestParam int userId,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate start) {
    //     try{
    //                 List <InterSchedule> schedules = schService.getDailyReport(userId, start);
    //                 String reportPath = "src\\main\\resources";
    //                 JasperReport jReport = JasperCompileManager.compileReport(reportPath + "\\sch.jrxml");
    //                 JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(schedules);
    //                 Map<String, Object> parameters = new HashMap<>();
    //                 parameters.put("schedules", schedules);
    //                 JasperPrint jasperprint = JasperFillManager.fillReport(jReport, parameters, jrBeanCollectionDataSource);
    //                 JasperExportManager.exportReportToPdfFile(jasperprint, "C:\\Downloads\\Report.pdf");
    //                 System.out.println("Done");
    //                 return ResponseEntity.status(HttpStatus.OK).body(schedules);
    //             }catch(Exception e){
    //                 System.out.print(e.getMessage());
    //                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); 
    //             }


    //         }
    // @GetMapping("/dailyReport")
    // public ResponseEntity<?> getDailyReport(
    //  @RequestParam int userId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate start)
    //         throws Exception, IOException{

    //             System.out.println("test");

    //             try {
    //                 String path = System.getProperty("java.class.path").split(",")[0].replace("src\\main\\resources\\Files", "").replace("src\\main\\resources\\Files", "")
    //             + "src\\main\\resources\\DailySchedule.jrxml";

    //             List<InterSchedule> exportFile = schRepo.dailyReport(userId, start);
    //             System.out.println("1. id"+userId+"date : "+start);
    //             File downloadFile = new File(path + exportFile);
    //             System.out.println("2. id"+userId+"date : "+start);
    //             InputStreamResource resouce = new InputStreamResource(new FileInputStream(downloadFile));
    //             System.out.println("3. id"+userId+"date : "+start);
    //             HttpHeaders header = new HttpHeaders();
    //             header.add(HttpHeaders.CONTENT_DISPOSITION, "filename = "+ downloadFile.getName());
    //     return ResponseEntity.ok()
    //     // .body("ok");
    //             .headers(header)
    //             .contentLength(downloadFile.length())
    //             .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
    //             .body(resouce);
       
    //             } catch (Exception e) {
    //                 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    //             }


        }


