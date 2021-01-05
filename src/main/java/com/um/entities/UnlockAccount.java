package com.um.entities;

import lombok.Data;

@Data
public class UnlockAccount {
	
	private String email;
	private String tempPwd;
	private String newPwd;
	private String confirmPwd;

}
