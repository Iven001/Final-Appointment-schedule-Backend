package com.project.appointment_schedule_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.appointment_schedule_management.dao.UserRepository;
import com.project.appointment_schedule_management.dto.JwtRequest;
import com.project.appointment_schedule_management.dto.JwtResponse;
import com.project.appointment_schedule_management.model.User;
import com.project.appointment_schedule_management.utils.JwtUtil;



@Service
public class JwtService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getEmail();
		String userPassword = jwtRequest.getPassword();
		
		authenticate(userName, userPassword);
		
		final UserDetails userDetails = loadUserByUsername(userName);
		
		String newGeneratedToken =  jwtUtil.generateToken(userDetails);
		
		User user = userRepo.findByMail(userName);
		
		return new JwtResponse (user , newGeneratedToken);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByMail(username);
		
		if(user != null) {
			return new org.springframework.security.core.userdetails.User(
					user.getMail(), 
					user.getPassword(),
					getAutorities(user));
		} else {
			throw new UsernameNotFoundException("UserEmail is not valid");
		}
	}
	
	private List<SimpleGrantedAuthority> getAutorities(User user) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		
		return authorities;
	}
	
	public void authenticate(String userName,String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("User is disabled");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad credentials from user");
		}
		
	}

}

