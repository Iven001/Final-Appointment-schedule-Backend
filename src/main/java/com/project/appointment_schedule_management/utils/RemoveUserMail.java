package com.project.appointment_schedule_management.utils;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.project.appointment_schedule_management.dto.ChangeOwnerDto;
import com.project.appointment_schedule_management.dto.SchduleDto;

@Component
public class RemoveUserMail {

    @Autowired
    private JavaMailSender mailSender;

    @Async
	public void sendEmail(String email,String schTitle)
			throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("r001adiance@gmail.com", "radiance001");
		helper.setTo(email);

		String subject = "Dear User , you have been successfully removed from the following meeting.";

		String content = "<span>Meeting -</span>"+"<b>"+schTitle+"</b>"
                + "<span>Click this to check your appointments </span>" + "<span><b><a href=\"" + "http://localhost:4200/login"
                + "\">to log in</a><b></spanss>";
				

		helper.setSubject(subject);
		helper.setText(content, true);

			mailSender.send(message);
	  
		
	}
}
