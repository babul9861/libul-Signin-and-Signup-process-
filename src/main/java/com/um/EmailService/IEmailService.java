package com.um.EmailService;

public interface IEmailService {
	
	public boolean sendAccountUnlockEmail(String subject, String body, String to);

}
