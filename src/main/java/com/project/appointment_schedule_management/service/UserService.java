package com.project.appointment_schedule_management.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.appointment_schedule_management.Interface.InterSchedule;
import com.project.appointment_schedule_management.Interface.UserDetails;
import com.project.appointment_schedule_management.Interface.UserInter;
import com.project.appointment_schedule_management.Interface.UserProfile;
import com.project.appointment_schedule_management.Interface.UserSchedule;
import com.project.appointment_schedule_management.Interface.WeeklyViewInter;
import com.project.appointment_schedule_management.dto.UserDetailsDto;
import com.project.appointment_schedule_management.dto.UserDto;
import com.project.appointment_schedule_management.exception.UserNotFoundException;
import com.project.appointment_schedule_management.model.User;


@Service
public interface UserService {

	//List<UserSchedule> searchUserSchedules(String title,LocalDate start,LocalDate end);

	List<InterSchedule> searchbyUserId(Integer userId);

	//Dejavu add
	List<UserSchedule> searchUserSchedules(Integer scheduleId);

	// List<UserSchedule> searchUserAndSchedule(Integer id);

	User findById(Integer id);

	List<UserDetails> searchUserDetails();

	List<UserProfile> searchUserProfile(Integer userId);

	// List<UserDetailsDto> searchUserDetailsDtos(String uname);

		//Dejavu
	// List<UserDetailsDto> searchUserDetailsDtos(String uname);
	List<UserDetailsDto> searchUserDetailsDtos();

	//Dejavu add
	List<UserSchedule> eventDetails(Integer scheduleId);
	
	User updateUser (User user, Integer userId);

	List<User> getAllUsers ();

    User saveUser (User user);

    User getUserById (int userId);

    User updateUser (User user);

    void deleteUser (int userId);
    
    void updateResetPasswordToken (String email , String token) throws UserNotFoundException;
    
    User getUserByResetPasswordToken (String token);

	User getUserByEmpId (int empId);
    
    void updatePassword (User user , String newPassword);

	List<InterSchedule> getAllUserScheduleMembers();

	List<WeeklyViewInter> getUserWeeklyViews(int userId,LocalDate start,LocalDate end);
	
	List<UserInter> getUserInfo ();

	
}
