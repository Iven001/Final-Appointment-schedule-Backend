package com.project.appointment_schedule_management.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.appointment_schedule_management.dao.UserRepository;
import com.project.appointment_schedule_management.dao.UserRequestRepository;
import com.project.appointment_schedule_management.model.UserRequest;
import com.project.appointment_schedule_management.service.UserRequestServiceImpl;
import com.project.appointment_schedule_management.service.UserServiceImpl;

@SpringBootTest
public class UserRequestService {
    @Mock
    private UserRequestRepository uRequestRepository;

    @InjectMocks
    private UserRequestServiceImpl uRequestServiceImpl;

    private static UserRequest uRequest;

    private static List<UserRequest> userRequests;
    
    @BeforeAll
    public static void runBeforeAll(){

        uRequest = new UserRequest();
        uRequest.setRequestId(1);
        uRequest.setUserRequest("hello");
        uRequest.setUserRequestId(2);
        uRequest.setScheduleRequestId(null);

        UserRequest uRequest2 = new UserRequest();
        uRequest2.setRequestId(2);
        uRequest2.setUserRequest("Hey");
        uRequest2.setUserRequestId(13);
        uRequest2.setScheduleRequestId(null);

        userRequests = new ArrayList<>();

        Collections.addAll(userRequests, uRequest, uRequest2);

    }

    @Test
    public void saveUserRequestTest(){
        when(this.uRequestRepository.save(uRequest)).thenReturn(uRequest);
        assertNotNull(this.uRequestRepository.save(uRequest));
    }

    @Test
    public void getAllUserRequestsTest(){
        when(this.uRequestRepository.findAll()).thenReturn(userRequests);
        assertEquals(this.uRequestServiceImpl.getAllUserRequests().size(), userRequests.size());
        verify(this.uRequestRepository, times(1)).findAll();
    }










}
