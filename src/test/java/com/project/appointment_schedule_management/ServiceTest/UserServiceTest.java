package com.project.appointment_schedule_management.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.project.appointment_schedule_management.dao.UserRepository;
import com.project.appointment_schedule_management.model.User;
import com.project.appointment_schedule_management.service.UserServiceImpl;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private static User user;

    private static List<User> users;


    @BeforeAll
    public static void runBeforeAll () {

        user = new User();
        user.setUserId(1);
       // user.setAuthority("member");
        user.setEmpId(202);
        user.setImgId(22);
        user.setMail("Jhon@gmail.com");
        user.setUname("Jhon");
        user.setPassword("123");
        user.setResetPasswordToken("hhhh1111");
        user.setTeam("T002");
        user.setBiography("Good");
        user.setNickName("Robot");

        User user2 = new User();
        user2.setUserId(2);
       // user2.setAuthority("organizer");
        user.setEmpId(203);
        user.setImgId(21);
        user.setMail("Mick@gmail.com");
        user.setUname("Mick");
        user.setPassword("123");
        user.setResetPasswordToken("hhhh1111");
        user.setTeam("T001");
        user.setBiography("Good");
        user.setNickName("Robot");
        
        users = new ArrayList<>();

        Collections.addAll(users, user, user2);
    }

    @Test
    public void getAllUsersTest () {
        when(this.userRepository.findAll()).thenReturn(users);

        assertEquals(this.userService.getAllUsers().size(), users.size());
        verify(this.userRepository, times(1)).findAll();
    }

    @Test
    public void findByIdTest () {
        when(this.userRepository.findById(1)).thenReturn(Optional.of(user));
        assertNotNull(this.userService.findById(1));

        // when(this.userRepository.findById(1)).thenReturn(Optional.empty());
        // assertNull(this.userService.findById(1));

        verify(this.userRepository, times(1)).findById(1);
    }

    

    
    
}
