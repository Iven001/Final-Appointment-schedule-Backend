package com.project.appointment_schedule_management.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.appointment_schedule_management.dto.ResetPasswordDto;
import com.project.appointment_schedule_management.exception.UserNotFoundException;
import com.project.appointment_schedule_management.model.User;
import com.project.appointment_schedule_management.service.UserService;
import com.project.appointment_schedule_management.utils.EmailAsync;

import net.bytebuddy.utility.RandomString;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/password")
public class ForgetPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailAsync emailTask;

    @PostMapping( "/forget_password" )
    public void processForgetPasswordForm(@RequestBody String email)
            throws UnsupportedEncodingException, MessagingException {
        String token = RandomString.make(5).toUpperCase();
        // String email = dto.getEmail();
        try {
            userService.updateResetPasswordToken(email, token);

            emailTask.sendEmail(email, token);
            System.out.println(email);

    
            // return new ResponseEntity<>("Success", HttpStatus.OK);

        } catch (UserNotFoundException e) {
            System.out.println("hello");
            // return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // @PostMapping ({"/forget_password"})
    // public ResponseEntity<String> processForgetPasswordForm(@RequestBody String
    // email)
    // throws UnsupportedEncodingException, MessagingException {
    // String token = RandomString.make(5);
    //// String email = dto.getEmail();
    // try {
    // userService.updateResetPasswordToken(email, token);
    //
    // emailTask.sendEmail(email,token);
    // System.out.println(email);
    //// return ResponseEntity.status(HttpStatus.OK).body(email);
    // return new ResponseEntity<>(email, HttpStatus.OK);
    //
    // } catch (UserNotFoundException e) {
    // System.out.println("hello");
    // return new ResponseEntity<>(null , HttpStatus.INTERNAL_SERVER_ERROR); }
    // }

    // private void sendEmail(String email,String token)
    // throws UnsupportedEncodingException, MessagingException {
    // MimeMessage message = mailSender.createMimeMessage();
    // MimeMessageHelper helper = new MimeMessageHelper(message);
    //
    // helper.setFrom("r001adiance@gmail.com", "radiance001");
    // helper.setTo(email);
    //
    // String subject = "Here's the link to change your password";
    //
    // String content = "<p>Hello</p>" + "<p>You have requested to reset your
    // password</p>"
    // + "<p>Click the link below to change your password</p>" + "<p><b><a href=\""
    // + "http://localhost:4200/password"
    // + "\">Change your password</a><b></p>"
    // +"<p>Use this OTP code to reset your password</p>"+"<b>"+token+"</b>"
    // + "<p>Ignore this email if you do remember your password or, you have not
    // made the right request</p>";
    //
    // helper.setSubject(subject);
    // helper.setText(content, true);
    //
    // mailSender.send(message);
    // }

    @PostMapping("/reset_password")
    public void resetPasswordForm(@RequestBody ResetPasswordDto dto) {
        User user = userService.getUserByResetPasswordToken(dto.getToken());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user == null) {
            System.out.println("Fail");
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userService.saveUser(user);
    }
}
