package com.project.appointment_schedule_management.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.appointment_schedule_management.Interface.InterSchedule;
import com.project.appointment_schedule_management.Interface.UserDetails;
import com.project.appointment_schedule_management.Interface.UserInter;
import com.project.appointment_schedule_management.Interface.UserProfile;
import com.project.appointment_schedule_management.Interface.UserSchedule;
import com.project.appointment_schedule_management.Interface.WeeklyViewInter;
import com.project.appointment_schedule_management.dto.UserDetailsDto;
import com.project.appointment_schedule_management.model.Image;
import com.project.appointment_schedule_management.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

        User save(Image i);

        // Dejavu add
        // to get event details
        @Query(value = "SELECT u.user_id as userId,u.name as uname,s.schedule_id as id,s.start_date as start,s.end_date as end,s.start_time as start_time,s.end_time as end_time,s.status as status,s.privacy as privacy,s.place as place,s.description as description,s.title as title "
                        +
                        "FROM appointment.user u,appointment.schedule s,appointment.users_schedule us " +
                        "WHERE s.schedule_id=us.schedule_id and u.user_id=us.user_id and s.schedule_id=:scheduleId", nativeQuery = true)
        List<UserSchedule> eventDetails(Integer scheduleId);

        // Dejavu add
        // to get attendees
        @Query(value = "SELECT u.user_id as userId,u.name as uname,s.schedule_id as id,s.start_date as start,s.end_date as end,s.start_time as start_time,s.end_time as end_time,s.status as status,s.privacy as privacy,s.place as place,s.description as description,s.title as title,t.team_name as teamName "
                        +
                        "FROM appointment.user u,appointment.schedule s,appointment.users_schedule us,appointment.team t "
                        +
                        "WHERE u.team=t.team_id and s.schedule_id=us.schedule_id and u.user_id=us.user_id and s.schedule_id=:scheduleId", nativeQuery = true)
        List<UserSchedule> searchUserSchedules(Integer scheduleId);

        @Query(value = "SELECT u.user_id as userId,u.name as uname,i.image_name as name,i.image_data as imageData,u.img_id as imageId,t.team_name as teamName,d.department_name as departmentName "
                        +
                        "FROM appointment.user u,appointment.image i,appointment.team t,appointment.department d " +
                        "WHERE u.img_id=i.id and u.team=t.team_id and t.department=d.department_id", nativeQuery = true)
        List<UserDetails> searchUserDetails();

        @Query(value = "SELECT u.user_id as userId,u.name as uname,s.schedule_id as scheduleId,s.start_date as start,s.end_date as end,s.start_time as start_time,s.end_time as end_time,s.description as description,s.place as place,s.privacy as privacy,s.status as status,s.title as title,s.is_delete as isDelete,s.owner as ownerId "
                        +
                        "FROM appointment.user u, appointment.schedule s, appointment.users_schedule us WHERE u.user_id=us.user_id and s.schedule_id=us.schedule_id and u.user_id=:userId", nativeQuery = true)
        List<InterSchedule> searchbyUserId(int userId);

        @Query(value = "SELECT u.user_id as userId,u.name as uname,u.mail as mail,u.password as password,t.team_name as teamName,u.biography as biography,u.nick_name as nickName,d.department_name as departmentName FROM appointment.user u,appointment.team t,appointment.department d WHERE u.team=t.team_id and t.department=d.department_id and u.user_id=:userId", nativeQuery = true)
        List<UserProfile> searchUserProfile(int userId);// userprofile for user

        // Dejavu added
        @Query("SELECT new com.project.appointment_schedule_management.dto.UserDetailsDto(u.userId,u.uname,i.name,i.imageData,u.imgId,t.teamName,d.departmentName) "
                        +
                        "FROM User u,Image i,Team t,Department d " +
                        "WHERE u.imgId=i.imageId and u.team=t.teamId and t.departmentId=d.departmentId")
        List<UserDetailsDto> searchUserDetailsDtos();

        // @Query("SELECT new
        // com.project.appointment_schedule_management.dto.UserDetailsDto(u.userId,u.uname,i.name,i.imageData,u.imgId,t.teamName,d.departmentName)
        // "+
        // "FROM User u,Image i,Team t,Department d "+
        // "WHERE u.imgId=i.imageId and u.team=t.teamId and
        // t.departmentId=d.departmentId and u.uname=:uname")
        // List<UserDetailsDto> searchUserDetailsDtos(String uname);//for search bar on
        // daily view

        @Query(value = "SELECT u.user_id as userId,u.name as uname,s.schedule_id as scheduleId,s.start_date as start,s.end_date as end,s.start_time as start_time,s.end_time as end_time,s.description as description,s.place as place,s.privacy as privacy,s.status as status,s.title as title,s.is_delete as isDelete,s.owner as ownerId "
                        +
                        "FROM appointment.user u, appointment.schedule s, appointment.users_schedule us WHERE u.user_id=us.user_id and s.schedule_id=us.schedule_id", nativeQuery = true)
        List<InterSchedule> getAllUserScheduleMembers();// getAll User from schedule

        @Query(value = "SELECT u.user_id as userId,s.schedule_id as scheduleId,s.title as title,s.description as description,s.start_date as start,s.start_time as start_time,s.end_date as end,s.end_time as end_time,s.status as status "
                        +
                        "FROM appointment.schedule s,appointment.user u,appointment.users_schedule us " +
                        "WHERE s.start_date BETWEEN :start AND :end and s.schedule_id=us.schedule_id and u.user_id=us.user_id and u.user_id=:userId", nativeQuery = true)
        List<WeeklyViewInter> getUserWeeklyViews(int userId, LocalDate start, LocalDate end);

        public User findByResetPasswordToken(String token);

        // void save(Optional<User> user);

        public User findByMail(String email);

        public User findByEmpId(int empId);
        
        @Query(value="Select u.user_id as userId,u.biography as biography,u.emp_id as empId,u.img_id as imgId,u.mail as mail,u.nick_name as nickName,u.password as password,u.reset_password_token as resetPasswordToken,u.team as team,u.name as uname,t.team_name as teamName,d.department_name as departmentName " +
        	    "From appointment.user u,appointment.team t,appointment.department d " +
        	    "Where u.team=t.team_id and u.user_id=u.user_id",nativeQuery=true)
        	    List<UserInter> getUserInfo ();
        
        
//        @Query(value = "SELECT u.user_id as userId,u.name as uname,s.schedule_id as scheduleId,s.start_date as start,s.end_date as end,s.start_time as start_time,s.end_time as end_time,s.description as description,s.place as place,s.privacy as privacy,s.status as status,s.title as title,s.is_delete as isDelete,s.owner as ownerId "
//                +
//                "FROM appointment.user u, appointment.schedule s, appointment.users_schedule us WHERE u.user_id=us.user_id and s.schedule_id=us.schedule_id", nativeQuery = true)
//        List<InterSchedule> getAllUser();// getAll User from schedule

}

// @Query("SELECT DISTINCT s FROM StudentEntity s INNER JOIN s.courses c WHERE
// c.courseName=:cName OR s.studentId=:sId OR s.studentName=:sName")
// List <StudentEntity> searchStudentsByCourse(String cName,String sId,String
// sName);
// Select
// u.user_id,u.name,t.team_name,d.department_name,u.img_id,i.image_data,i.name
// From appointment.image i
// inner join appointment.user u on u.img_id = i.id
// inner join appointment.team t on t.team_id = u.team
// inner join appointment.department d on d.department_id= t.department
// where upper (u.name) like concat ('w','%');

// @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%',
// ?1,'%'))")
// List<Tutorial> findByTitleLikeCaseInsensitive(String title);
// u.userId,u.uname,s.id,s.start,s.end,s.start_time,s.end_time,s.title,s.description,s.privacy,s.place,s.status