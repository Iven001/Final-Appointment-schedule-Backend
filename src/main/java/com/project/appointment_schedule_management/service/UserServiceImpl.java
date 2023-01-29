package com.project.appointment_schedule_management.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.appointment_schedule_management.Interface.InterSchedule;
import com.project.appointment_schedule_management.Interface.UserDetails;
import com.project.appointment_schedule_management.Interface.UserInter;
import com.project.appointment_schedule_management.Interface.UserProfile;
import com.project.appointment_schedule_management.Interface.UserSchedule;
import com.project.appointment_schedule_management.Interface.WeeklyViewInter;
import com.project.appointment_schedule_management.dao.UserRepository;
import com.project.appointment_schedule_management.dto.UserDetailsDto;
import com.project.appointment_schedule_management.exception.UserNotFoundException;
import com.project.appointment_schedule_management.model.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;

	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Autowired
	private EmployeeService empService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> getAllUsers() {
		List<User> list = userRepo.findAll();
		return list;
	}

	@Override
	public User findById(Integer id) {
		return userRepo.findById(id).get();
	}

	// @Override
	// public List<UserSchedule> searchUserAndSchedule(Integer id) {
	// List<UserSchedule> list = userRepo.searchUserAndSchedule(id);
	// return list;
	// }

	@Override
	public List<InterSchedule> searchbyUserId(Integer userId) {
		List<InterSchedule> list = userRepo.searchbyUserId(userId);
		return list;
	}

	@Override
	public List<UserDetails> searchUserDetails() {
		List<UserDetails> list = userRepo.searchUserDetails();
		return list;
	}

		//Dejavu
		@Override
		public List<UserSchedule> searchUserSchedules(Integer scheduleId) {
			List<UserSchedule> list=userRepo.searchUserSchedules(scheduleId);
			return list;
		}


		//Dejavu
	@Override
	public List<UserSchedule> eventDetails(Integer scheduleId) {
		List<UserSchedule> list=userRepo.eventDetails(scheduleId);
		return list;
	}

	// @Override
	// public List<UserSchedule> searchUserSchedules(String title, LocalDate start, LocalDate end) {
	// 	List<UserSchedule> list = userRepo.searchUserSchedules(title, start, end);
	// 	return list;
	// }

	@Override
	public List<UserProfile> searchUserProfile(Integer userId) {
		List<UserProfile> list = userRepo.searchUserProfile(userId);
		return list;
	}

	// @Override
	// public List<UserDetailsDto> searchUserDetailsDtos(String uname) {
	// 	List<UserDetailsDto> list = (List<UserDetailsDto>) userRepo.searchUserDetailsDtos(uname);
	// 	return list;
	// }

	//Dejavu
	@Override
	public List<UserDetailsDto> searchUserDetailsDtos() {
		List<UserDetailsDto> list = (List<UserDetailsDto>) userRepo.searchUserDetailsDtos();
		return list;
	}

	@Override
	public User updateUser(User user, Integer userId) {

		return userRepo.save(user);
	}

	@Override
	public List<InterSchedule> getAllUserScheduleMembers() {
		List<InterSchedule> list = userRepo.getAllUserScheduleMembers();
		return list;
	}

	@Override
	public User getUserById(int userId) {

		return userRepo.findById(userId).get();
	}

	@Override
	public User updateUser(User user) {

		return userRepo.save(user);
	}

	@Override
	public void deleteUser(int userId) {

		userRepo.deleteById(userId);

	}

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	@Override
	public User saveUser(User user) {
		return userRepo.save(user);
	}

	@Override
	public void updateResetPasswordToken(String email, String token) throws UserNotFoundException {
		User user = userRepo.findByMail(email);

		if (user != null) {
			user.setResetPasswordToken(token);
			userRepo.save(user);
		} else {
			throw new UserNotFoundException("Could not find any user with email " + email);
		}

	}

	@Override
	public User getUserByResetPasswordToken(String token) {

		return userRepo.findByResetPasswordToken(token);
	}

	@Override
	public void updatePassword(User user, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);

		user.setPassword(encodedPassword);
		user.setResetPasswordToken(null);
		userRepo.save(user);

	}

	@Override
	public List<WeeklyViewInter> getUserWeeklyViews(int userId, LocalDate start, LocalDate end) {
		List<WeeklyViewInter> list = userRepo.getUserWeeklyViews(userId, start, end);
		return list;
	}

	@Override
	public User getUserByEmpId(int empId) {
		return userRepo.findByEmpId(empId);
	}

	@Override
	public List<UserInter> getUserInfo() {
		List<UserInter> list = userRepo.getUserInfo();
		return list;
	}



}
