package com.um.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.um.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	
	User findByEmailId(String emailId);
	User findByEmailIdAndPassword(String emailId, String pwd);
	
	

}
