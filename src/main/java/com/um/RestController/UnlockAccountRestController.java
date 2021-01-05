package com.um.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.um.UserService.IUserService;
import com.um.entities.UnlockAccount;

@RestController
public class UnlockAccountRestController {
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/unlockUserAccount")
	public String unlockUserAccount(@RequestBody UnlockAccount ua) {
		
		if(userService.isTempPwdValid(ua.getEmail(), ua.getTempPwd())) {
			
			userService.unlockAccount(ua.getEmail(), ua.getNewPwd());
			return "Account Unlocked. Please proceed with login";
		}
		
		return "Please enter valid Temporary password";
		
		
	}
	
	

}
