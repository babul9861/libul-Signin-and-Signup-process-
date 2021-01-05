package com.um.UserServiceImpl;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.um.EmailService.IEmailService;
import com.um.Repo.CityRepo;
import com.um.Repo.CountryRepo;
import com.um.Repo.StateRepo;
import com.um.Repo.UserRepo;
import com.um.UserService.IUserService;
import com.um.entities.City;
import com.um.entities.Country;
import com.um.entities.State;
import com.um.entities.User;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private StateRepo stateRepo;

	@Autowired
	private CityRepo cityRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private IEmailService emailService;


	@Override
	public Map<Integer, String> findCountries() {
		List<Country> countryList = countryRepo.findAll();
		Map<Integer, String> countries = new HashMap<>();
		countryList.forEach(country -> {
			countries.put(country.getCountryId(), country.getCountryName());
		});

		return countries;
	}

	@Override
	public Map<Integer, String> findStates(Integer countryId) {
		Map<Integer, String> states = new HashMap<>();
		List<State> stateList = stateRepo.findByCountryId(countryId);
		stateList.forEach(state -> {
			states.put(state.getStateId(), state.getStateName());
		});
		return states;
	}

	@Override
	public Map<Integer, String> findCities(Integer stateId) {
		Map<Integer, String> cities = new HashMap<>();
		List<City> cityList = cityRepo.findByStateId(stateId);
		cityList.forEach(city -> {
			cities.put(city.getCityId(), city.getCityName());
		});
		return cities;
	}

	@Override
	public boolean isEmailUnique(String emailId) {
		User userDetails = userRepo.findByEmailId(emailId);
		if (userDetails == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean saveUser(User user) {
		user.setPassword(passwordGenerator());
		user.setAcc_status("Locked");
		User userObj = userRepo.save(user);

		String emailBody= getUnlockAccEmailBody(user);
		String subject="UNLOCK YOUR ACCOUNT | IES";

		boolean isSent = emailService.sendAccountUnlockEmail(subject, emailBody, user.getEmailId());

		return userObj.getUserId() != null && isSent;
	}

	private String passwordGenerator() {

		char[] password = new char[5];
		String alphaNumeric = "ascsajfdhjdshf1231243124";
		Random randomPwd = new Random();
		for (int i = 0; i < 5; i++) {
			password[i] = alphaNumeric.charAt(randomPwd.nextInt(alphaNumeric.length()));
		}
		System.out.println(password.toString());
		return password.toString();

	}

	// test-1 : invalid email and password ===> INVALID CREDENTIALS
	// test-2 : valid email and password and ac locked ===> Account Locked
	// test-3 : valid email and password and acc unlocked ==> Login Success

	@Override
	public String loginCheck(String emailId, String pwd) {
		User userDetails = userRepo.findByEmailIdAndPassword(emailId, pwd);
		if (userDetails != null) {
			if (userDetails.getAcc_status().equals("LOCKED")) {
				return "Account_Locked";
			} else {
				return "Login_Success";
			}

		}

		return "INVALID CREDENTIALS";
	}

	// test-1: User has given invalid temp-pwd ==> false
	// test-2: User has given valid temp-pwd ==> true

	@Override
	public boolean isTempPwdValid(String emailId, String tempPwd) {
		User userDetails = userRepo.findByEmailIdAndPassword(emailId, tempPwd);
		if(userDetails== null) {
			return false;
		}
		return true;
	}

	@Override
	public boolean unlockAccount(String emailId, String newPwd) {
		User userDetails = userRepo.findByEmailId(emailId);
		userDetails.setPassword(newPwd);
		userDetails.setAcc_status("UNLOCKED");
		try {
			userRepo.save(userDetails);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// test-1: User entered valid email ==> return existing pwd
	// test-2: User entered invalid email ==> NPE

	@Override
	public String forgotPassword(String emailId) {
		User userDetails = userRepo.findByEmailId(emailId);
		if (userDetails != null) {
			return userDetails.getPassword();
		}
		return null;
	}

//	private String getUnlockAccEmailBody(User user) {
//
//		StringBuffer sb = new StringBuffer("");
//		String body=null;
//		try {
//
//			File f = new File("unlock-acc-email-body.txt");
//
//			FileReader fr = new FileReader(f);
//
//			BufferedReader br = new BufferedReader(fr);
//
//			String line = br.readLine();
//
//			while (line != null) {
//				sb.append(line);
//				line = br.readLine();
//			}
//
//			br.close();
//
//			body=sb.toString();
//			body = body.replace("{FNAME}", user.getFName());
//			body = body.replace("{LNAME}", user.getLName());
//			body = body.replace("{TEMP-PWD}", user.getPassword());
//			body = body.replace("{EMAIL}", user.getEmailId());
//		}
//
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return body;
//	}
	
	// getRegSuccMailBody
	
	public String getUnlockAccEmailBody(User user) {
		String fileName = "unlock-acc-email-body.txt";
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(fileName, "");
			Stream<String> lines = Files.lines(path);
			replacedLines = lines.map(line -> line.replace("{FNAME}", user.getFName())
											.replace("{LNAME}", user.getLName())
											.replace("{TEMP-PWD}", user.getPassword())
											.replace("{EMAIL}", user.getEmailId()))
											.collect(Collectors.toList());
			mailBody = String.join("", replacedLines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
}
}
