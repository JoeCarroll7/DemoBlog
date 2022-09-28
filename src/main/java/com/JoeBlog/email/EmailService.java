package com.JoeBlog.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

	private final JavaMailSender javaMailSender;
	private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
	
	@Override
	@Async
	public void send(String toEmail, String email) {
		try {
			MimeMessage mineMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mineMessage, "utf-8");
			mimeMessageHelper.setText("test", true);
			mimeMessageHelper.setTo(toEmail);
			mimeMessageHelper.setSubject("Confirm your email");
			mimeMessageHelper.setFrom("joeapptestermail@gmail.com");
		}catch(MessagingException e) {
			LOGGER.error("failed to send email", e);
			throw new IllegalStateException("failed to send");
		}
	}
	
	public void testEmail(String email, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("joeapptestermail@gmail.com");
		message.setTo(email);
		message.setText(content);
		message.setSubject("confirm email");
		
		javaMailSender.send(message);
	}

}
