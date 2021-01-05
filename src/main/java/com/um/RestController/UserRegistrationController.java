package com.um.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.um.UserService.IUserService;
import com.um.entities.User;

@RestController
public class UserRegistrationController {
	
	
@Autowired
	private IUserService userService;
	
	@GetMapping("/getCountries")
	public Map<Integer, String> findContries(){
		return userService.findCountries();
	}
	
	@GetMapping("/getStates/{countryId}")
	public Map<Integer, String> findStates(@PathVariable Integer countryId){
		return userService.findStates(countryId);
	}
	@GetMapping("/getCities/{stateId}")
	public Map<Integer, String> findCities(@PathVariable Integer stateId){
		return userService.findCities(stateId);
	}
	
	@GetMapping("/emailCheck/{email}")
	public String isEmailUnique(@PathVariable String email)
	{
		if(userService.isEmailUnique(email)) 
		{
			return "UNIQUE";
		}
		else {
			return "DUPLICATE";
		}
		
	}
	
	
	@PostMapping("/registration")
	public ResponseEntity<String> userRegistration(@RequestBody User user) {
		if(userService.isEmailUnique(user.getEmailId())) {
			
			userService.saveUser(user);
			
			return new ResponseEntity<>("Registration Success", HttpStatus.CREATED);
			
		}
		else {
			return new ResponseEntity<>("Registration Success", HttpStatus.BAD_REQUEST);
		}
		
	}
	


}
