package com.um.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.um.UserService.IUserService;
import com.um.entities.LoginForm;

@RestController
@CrossOrigin
public class UserLoginRestController {
    
	@Autowired
	private IUserService userService;
	
	@PostMapping("/login")
	public String userLogin(@RequestBody LoginForm loginForm) {
		return userService.loginCheck(loginForm.getEmail(), loginForm.getPwd());
		
	}
}
