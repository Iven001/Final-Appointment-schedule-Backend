package com.project.appointment_schedule_management.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name ="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "user_id")
	private int userId;

	@Column(name= "emp_id",nullable = false, updatable = false)
	private int empId;

	@Column(name= "name")
	private String uname;

	@Column(name= "password")
	private String password;

	@Column(name= "mail",nullable = false,unique = true)
	private String mail;

	@Column(name= "team")
	private String team;

	@Column(name= "reset_password_token")
	private String resetPasswordToken;

	@Column( name= "Img_id")
	@ColumnDefault(value ="0")
	private int imgId;

	@Column(name = "biography")
	private String biography;

	@Column(name = "nick_name")
	private String nickName;

	@ManyToMany(mappedBy = "members")
	@JsonIgnore
	private List <Schedule> schedules;

 
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
			joinColumns = {
					@JoinColumn(name = "user_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "role_id")
			}
			)		
	private List<Role> role;



	
	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	
	
	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public int getImgId() {
		return imgId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getEmpId() {
		return empId;
	}
	
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}
	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	



	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	// public User(Integer userId, Integer empId, String uname, String password, String mail, String team,
	// 		String resetPasswordToken, String authority, Integer imgId, String biography, String nickName,
	// 		List<Schedule> schedules) {
	// 	this.userId = userId;
	// 	this.empId = empId;
	// 	this.uname = uname;
	// 	this.password = password;
	// 	this.mail = mail;
	// 	this.team = team;
	// 	this.resetPasswordToken = resetPasswordToken;
	// 	this.authority = authority;
	// 	this.imgId = imgId;
	// 	this.biography = biography;
	// 	this.nickName = nickName;
	// 	this.schedules = schedules;
	// }

	// public User(Integer userId, Integer empId, String uname, String password, String mail, String team,
	// 		String resetPasswordToken, String authority, Integer imgId, String biography, String nickName) {
	// 	this.userId = userId;
	// 	this.empId = empId;
	// 	this.uname = uname;
	// 	this.password = password;
	// 	this.mail = mail;
	// 	this.team = team;
	// 	this.resetPasswordToken = resetPasswordToken;
	// 	this.authority = authority;
	// 	this.imgId = imgId;
	// 	this.biography = biography;
	// 	this.nickName = nickName;
	// }

	// public User() {
	// }

	// @Override
	// public String toString() {
	// 	return "User [userId=" + userId + ", empId=" + empId + ", uname=" + uname + ", password=" + password + ", mail="
	// 			+ mail + ", team=" + team + ", resetPasswordToken=" + resetPasswordToken + ", authority=" + authority
	// 			+ ", imgId=" + imgId + ", biography=" + biography + ", nickName=" + nickName + ", schedules="
	// 			+ schedules + "]";
	// }


	
	
	

}
