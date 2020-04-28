package com.palette.controller;

import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;


public class MemberEmailController {
	@Autowired
	JavaMailSender javaMailSender;
	
	@Async
	public void sendMail() throws MessagingException{
		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("hi");
		message.setRecipient(Message.RecipientType.TO, new InternetAddress("recipient email"));
		message.setText("subHi");
		message.setSentDate(new Date());
		javaMailSender.send(message);
	}

}
