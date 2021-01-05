package com.um.EmailServiceImpl;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.um.EmailService.IEmailService;

@Service
public class EmailServiceImpl implements IEmailService {

	
	JavaMailSender mailSender;

	@Override
	public boolean sendAccountUnlockEmail(String subject, String body, String to) {
		
		try {
			
			MimeMessage mailMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
				
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			
			mailSender.send(mailMessage);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//	@Override
//	public boolean sendAccountUnlockEmail(String subject, String body, String to) 
//	{
//		try {
//
//			SimpleMailMessage mailMessage= new SimpleMailMessage();
//			mailMessage.setTo(to);
//			mailMessage.setSubject(subject);
//			mailMessage.setText(body);
//			mailSender.send(mailMessage);
//			return true;
//
//		}
//
//		catch(Exception e) {
//
//			e.printStackTrace();
//
//		}
//
//		return false;
//
//	}

}
