package com.project.appointment_schedule_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.appointment_schedule_management.dao.UserRequestRepository;
import com.project.appointment_schedule_management.dto.UserRequestDto;
import com.project.appointment_schedule_management.model.UserRequest;
import com.project.appointment_schedule_management.service.UserRequestService;

@CrossOrigin(origins = "http://localhost:4200" )
@RestController
@RequestMapping("/userRequest" )
public class UserRequestController {

    private final UserRequestRepository userRequestRepository;
    private final UserRequestService userRequestService;

    public UserRequestController(UserRequestRepository userRequestRepository, UserRequestService userRequestService) {
        this.userRequestRepository = userRequestRepository;
        this.userRequestService = userRequestService;
    }

    @GetMapping("/allUserRequest")
    public ResponseEntity<?> getAllUsersRequest () {

        try {
         return ResponseEntity.status(HttpStatus.OK).body(userRequestService.getAllUserRequests());

        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    // @PostMapping
    // public ResponseEntity<?> newUserRequest (@RequestBody UserRequestDto dto) {

    //     try {
    //           UserRequest newRequest =  userRequestService.saveUserRequest(dto);
    //             return ResponseEntity.status(HttpStatus.CREATED).body(newRequest);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    //     }
        
    // }

    @PostMapping("/newRequest/{id}")
    public ResponseEntity<?> UserRequest (@RequestBody UserRequestDto dto,@PathVariable("id") Integer userId) {

        try {
              //UserRequest newRequest =  userRequestService.saveUserRequest(dto);
              UserRequest newRequest = new UserRequest();
              newRequest.setRequestId(dto.getRequestId());
              newRequest.setUserRequest(dto.getUserRequest());
              newRequest.setUserRequestId(userId);
              newRequest.setScheduleRequestId(dto.getScheduleRequestId());
                return ResponseEntity.status(HttpStatus.CREATED).body(userRequestService.savetoUserRequest(newRequest));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }
    
    
}
