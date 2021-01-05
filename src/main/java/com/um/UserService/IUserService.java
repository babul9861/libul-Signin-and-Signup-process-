package com.um.UserService;

import java.util.Map;


import com.um.entities.User;


public interface IUserService {
	
	//Registration page operations
			public Map<Integer, String> findCountries();

			public Map<Integer, String> findStates(Integer countryId);

			public Map<Integer, String> findCities(Integer stateId);

			public boolean isEmailUnique(String emailId);

			public boolean saveUser(User user);

			//Login page operations
			public String loginCheck(String email, String pwd);

			//Unlock Account Operations
			public boolean isTempPwdValid(String email, String tempPwd);

			public boolean unlockAccount(String email, String newPwd);

			//Forgot Password Operations
			public String forgotPassword(String email);


}
