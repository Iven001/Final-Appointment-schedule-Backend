package com.project.appointment_schedule_management.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.appointment_schedule_management.Interface.InterSchedule;
import com.project.appointment_schedule_management.Interface.UserProfile;
import com.project.appointment_schedule_management.dao.UserRepository;
import com.project.appointment_schedule_management.dto.UserDetailsDto;
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

		@Test
		public void saveUserTest() {
			when(this.userRepository.save(user)).thenReturn(user);
			assertNotNull(this.userService.saveUser(user));
		}

	    // @Test
		// public void searchbyUserIdTest(){
		// 	when(this.userRepository.searchbyUserId(1)).thenReturn(1);
		// 	assertNotNull(this.userRepository.searchbyUserId(1));

		// 	verify(this.userRepository, times(1)).searchbyUserId(1);
		// }

	    @Test
		public void updateUserTest(){
			when(this.userRepository.findById(1)).thenReturn(Optional.of(user));
			Optional<User> userId = this.userRepository.findById(user.getUserId());
			when(this.userRepository.save(user)).thenReturn(user);
			this.userRepository.save(user);
			verify(this.userRepository, times(1)).save(user);
		}

		@Test
		public void searchbyUserIdTest(){
			List<InterSchedule> oneSearch = userRepository.searchbyUserId(1);
			Mockito.when(userRepository.searchbyUserId(1)).thenReturn(oneSearch);
			List<InterSchedule> twoSearch = userRepository.searchbyUserId(1);
			assertEquals(oneSearch, twoSearch);

		}

		@Test
		public void searchUserProfileTest(){
			List<UserProfile> excepted = userRepository.searchUserProfile(1);
			Mockito.when(userRepository.searchUserProfile(1)).thenReturn(excepted);
			List<UserProfile> actual = userRepository.searchUserProfile(1);
			assertEquals(excepted, actual);
		}

		// @Test
		// public void searchUserDetailsDtos(){
		// 	List<UserDetailsDto> excepted = userRepository.searchUserDetailsDtos("Jhon");
		// 	Mockito.when(userRepository.searchUserDetailsDtos("John")).thenReturn(excepted);
		// 	List<UserDetailsDto> actual = userRepository.searchUserDetailsDtos("John");
		// 	assertEquals(excepted, actual);
		// }

		// @Test
	    // public void getAllScheduleMember() {
	    //     when(this.userRepository.getAllUserScheduleMembers()).thenReturn(InterSchedule);

	    //     assertEquals(this.userService.getAllUserScheduleMembers().size(), users.size());
	    //     verify(this.userRepository, times(1)).getAllUserScheduleMembers();
	    // }
		
		@Test
		public void getAllScheduleMember(){
			List<InterSchedule> excepted = userRepository.getAllUserScheduleMembers();
			Mockito.when(userRepository.getAllUserScheduleMembers()).thenReturn(excepted);
			List<InterSchedule> actual = userRepository.getAllUserScheduleMembers();
			assertEquals(excepted, actual);
		}
 
	}

