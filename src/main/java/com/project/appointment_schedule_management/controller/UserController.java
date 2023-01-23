package com.project.appointment_schedule_management.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.appointment_schedule_management.Interface.InterSchedule;
import com.project.appointment_schedule_management.Interface.UserDetails;
import com.project.appointment_schedule_management.Interface.UserProfile;
import com.project.appointment_schedule_management.Interface.UserSchedule;
import com.project.appointment_schedule_management.Interface.WeeklyViewInter;
import com.project.appointment_schedule_management.dao.UserRepository;
import com.project.appointment_schedule_management.dto.ChangeOwnerDto;
import com.project.appointment_schedule_management.dto.DailyViewDto;
import com.project.appointment_schedule_management.dto.UpdateNickAndBio;
import com.project.appointment_schedule_management.dto.UserDetailsDto;
import com.project.appointment_schedule_management.dto.UserDto;
import com.project.appointment_schedule_management.model.User;
import com.project.appointment_schedule_management.model.Employee;
import com.project.appointment_schedule_management.model.Role;
import com.project.appointment_schedule_management.service.EmployeeService;
import com.project.appointment_schedule_management.service.UserService;
import com.project.appointment_schedule_management.utils.ImageUtils;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {

    private final EmployeeService empService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(EmployeeService empService, PasswordEncoder passwordEncoder, UserRepository userRepository,
            UserService userService) {
        this.empService = empService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/allUser")
    public ResponseEntity<?> getAllUsers() {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/userWeeklyView")
    public ResponseEntity<?> getUserWeeklyView(
            @RequestParam(value = "start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(value = "end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam int userId) {

        try {
            List<WeeklyViewInter> list = userService.getUserWeeklyViews(userId, start, end);
            return ResponseEntity.status(HttpStatus.OK).body(list);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @GetMapping("/searchUserDetails") // search for start name
    public ResponseEntity<?> searchUserDetails() {

        try {
            List<UserDetails> list = userService.searchUserDetails();
            if (list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Foound");
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/serchUserSchedule") // search for only userdetails with userId
    public ResponseEntity<?> searchUserSchedule(@RequestParam int userId) {
        // int userId = Integer.parseInt(userI);
        LocalDate now = LocalDate.now();
        String cDate = now.toString();
        try {
            List<InterSchedule> list = userService.searchbyUserId(userId);
            List<DailyViewDto> dtoList = new ArrayList<DailyViewDto>();
            if (list.size() == 0) {
                DailyViewDto dtoNull = new DailyViewDto();
                User user = userService.findById(userId);
                dtoNull.setCurrentUserName(user.getUname());

                dtoList.add(dtoNull);
            }
            for (int i = 0; i < list.size(); i++) {
                String start = list.get(i).getStart().toString();
                String end = list.get(i).getEnd().toString();
                if (start.equals(end)) {
                    DailyViewDto dto = new DailyViewDto();
                    dto.setCuurentUserId(userId);
                    dto.setScheduleId(list.get(i).getScheduleId());
                    dto.setCurrentUserName(list.get(i).getUname());
                    dto.setStart(list.get(i).getStart());
                    dto.setEnd(list.get(i).getEnd());
                    dto.setStartTime(list.get(i).getStart_time());
                    dto.setEndTime(list.get(i).getEnd_time());
                    dto.setTitle(list.get(i).getTitle());
                    dto.setPrivacy(list.get(i).getPrivacy());
                    dto.setStatus(list.get(i).getStatus());
                    dto.setIsDelete(list.get(i).getIsDelete());
                    dto.setOwnerId(list.get(i).getOwnerId());

                    dtoList.add(i, dto);

                } else {
                    LocalDate startDate = list.get(i).getStart();
                    LocalDate endDate = list.get(i).getEnd();

                    Period period = Period.between(list.get(i).getStart(), list.get(i).getEnd());
                    int diff = Math.abs(period.getDays() + 1);

                    // List<LocalDate> listOfDates =
                    // startDate.datesUntil(endDate).collect(Collectors.toList());

                    List<LocalDate> listOfDates = Stream.iterate(startDate, date -> date.plusDays(1))
                            .limit(diff)
                            .collect(Collectors.toList());

                    for (int j = 0; j < diff; j++) {
                        DailyViewDto dto = new DailyViewDto();
                        dto.setCuurentUserId(userId);
                        dto.setScheduleId(list.get(i).getScheduleId());
                        dto.setCurrentUserName(list.get(i).getUname());
                        dto.setStart(listOfDates.get(j));
                        dto.setEnd(listOfDates.get(j));
                        dto.setStartTime(list.get(i).getStart_time());
                        dto.setEndTime(list.get(i).getEnd_time());
                        dto.setTitle(list.get(i).getTitle());
                        dto.setPrivacy(list.get(i).getPrivacy());
                        dto.setStatus(list.get(i).getStatus());
                        dto.setIsDelete(list.get(i).getIsDelete());
                        dto.setOwnerId(list.get(i).getOwnerId());

                        dtoList.add(i, dto);

                    }
                }
                for (DailyViewDto d : dtoList){
                    if (d.getStart().isBefore(now)){
                        d.setStatus("expired");
                    }
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(dtoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Dejavu added
    @GetMapping("/serchMeetingSchedule") // search for only Meeting with title&date
    public ResponseEntity<?> searchUserMeeting(@RequestParam("scheduleId") Integer scheduleId) {

        try {
            List<UserSchedule> userSchedule = userService.searchUserSchedules(scheduleId);
            if (userSchedule == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Schedule Not Found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(userSchedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    

    @GetMapping("/serchUserProfile") // search for only userdetails with userId
    public ResponseEntity<?> searchProfile(@RequestParam Integer userId) {

        try {
            List<UserProfile> list = userService.searchUserProfile(userId);
            if (list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserProfile Not Found :" + userId);
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    // Dejavu add
    @GetMapping("/serchUsesDetails") // search for only userdetails with userId
    public ResponseEntity<?> search() {

        try {
            List<UserDetailsDto> list = userService.searchUserDetailsDtos();
            for (int i = 0; i < list.size(); i++) {
                byte[] imageData = list.get(i).getImageData();
                System.out.println("User Deatils image " + i + " : " + imageData);
                byte[] decodedImage = ImageUtils.decompressImage(imageData);
                System.out.println("Decoded Image : " + decodedImage);

                // store decoded image in list
                list.get(i).setImageData(decodedImage);

            }

            if (list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserDetails Not Found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getMembers") // get all user and Schedule
    public ResponseEntity<?> getAllUserScheduleMembers() {

        try {
            List<InterSchedule> list = userService.getAllUserScheduleMembers();
            if (list.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member and Schedule Not Found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateUserProfile")
    public ResponseEntity<?> userUpdate(@RequestBody UpdateNickAndBio dto, @RequestParam String password) {

        try {
            User user = userService.findById(dto.getUserId());
            String ps = user.getPassword();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(password, ps)) {
                user.setBiography(dto.getBiography());
                user.setNickName(dto.getNickName());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check Your User Information!!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> userPasswordChange(@RequestBody ChangeOwnerDto dto) {

        try {
            User user = userService.findById(dto.getCurrentUserId());
            String ps = user.getPassword();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(dto.getOldPassword(), ps)) {
                //user.setPassword(dto.getNewPassword());
                user.setPassword(getEncodedPassword(dto.getNewPassword()));
            } 
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Check Your User Information!!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    // Dejavu add
    @GetMapping("/eventDetails")
    public ResponseEntity<?> getEventDetail(@RequestParam Integer scheduleId) {
        try {
            List<UserSchedule> userSchedule = userService.eventDetails(scheduleId);
            if (userSchedule == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Schedule Not Found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(userSchedule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // SpringSecurity
    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {

        // try {
        Employee emp = empService.getEmployeeById(userDto.getEmpId());
        int empId = emp.getEmployeeId();
        if (userDto.getEmpId() == empId) {
            String roleName = null;
            if (emp.getPosition().equals("Team Leader") ||
                    emp.getPosition().equals("Manager")) {
                roleName = "organizer";
            } else if(emp.getPosition().equals("Admin")){
                roleName = "admin";
            } else {
                roleName = "member";
            }

            String team = emp.getTeam();
            List<Role> roleList = new ArrayList();
            Role role = new Role();
            role.setRoleName(roleName);
            role.setRoleDescription("This user has " + roleName + " permission");
            roleList.add(role);

            User user = new User();
            // user.setUserId(1);
            user.setEmpId(userDto.getEmpId());
            user.setUname(userDto.getUname());
            user.setMail(userDto.getMail());
            user.setPassword(getEncodedPassword(userDto.getPassword()));
            user.setRole(roleList);
            user.setTeam(team);
            user.setImgId(25);
            userService.saveUser(user);

            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);


    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @GetMapping({ "/getUserInfo" })
    public String getUserInfo() {
        // System.out.println(SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }

}