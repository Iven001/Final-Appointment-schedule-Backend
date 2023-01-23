package com.project.appointment_schedule_management.utils;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailAsync {
	
	@Autowired
	private JavaMailSender mailSender;

	@Async
	public void sendEmail(String email,String token)
			throws UnsupportedEncodingException, MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom("r001adiance@gmail.com", "radiance001");
		helper.setTo(email);

		String subject = "Here's the link to change your password";

		String content = "<p>Hello</p>" + "<p>You have requested to reset your password</p>"
				+ "<p>Click the link below to change your password</p>" + "<p><b><a href=\"" + "http://localhost:4200/password"
				+ "\">Change your password</a><b></p>"
				+"<p>Use this OTP code to reset your password</p>"+"<b>"+token+"</b>"
				+ "<p>Ignore this email if you do remember your password or, you have not made the right request</p>";

		helper.setSubject(subject);
		helper.setText(content, true);

			mailSender.send(message);
	  
		
	}
}

