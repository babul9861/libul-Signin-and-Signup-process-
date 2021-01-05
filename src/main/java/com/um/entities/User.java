package com.um.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
@Data
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer userId;
	private String fName;
	private String lName;
	private String emailId;
	private Integer phno;
	private Integer dob;
	private Integer Country;
	private Integer state;
	private Integer city;
	private String password;
	private String acc_status;
	

}
