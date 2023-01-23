package com.project.appointment_schedule_management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.appointment_schedule_management.dao.UserRequestRepository;
import com.project.appointment_schedule_management.dto.UserRequestDto;
import com.project.appointment_schedule_management.model.UserRequest;

@Service
public class UserRequestServiceImpl implements UserRequestService {

    private final UserRequestRepository userRequestRepo;


    @Autowired
    public UserRequestServiceImpl(UserRequestRepository userRequestRepo) {
        this.userRequestRepo = userRequestRepo;
    }

    @Override
    public List<UserRequest> getAllUserRequests() {
        List<UserRequest> list = userRequestRepo.findAll();
        return list;
    }

	@Override
	public UserRequest savetoUserRequest(UserRequest rq) {
		
		return userRequestRepo.save(rq);
	}
    
}
