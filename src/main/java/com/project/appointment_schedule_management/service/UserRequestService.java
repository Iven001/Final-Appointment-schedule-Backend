package com.project.appointment_schedule_management.service;

import java.util.List;

import com.project.appointment_schedule_management.dto.UserRequestDto;
import com.project.appointment_schedule_management.model.UserRequest;

public interface UserRequestService {

   // UserRequest saveUserRequest(UserRequestDto dto);

    List<UserRequest> getAllUserRequests();

    UserRequest savetoUserRequest (UserRequest rq);
    
}
