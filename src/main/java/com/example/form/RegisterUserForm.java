package com.example.form;

import lombok.Data;

@Data
public class RegisterUserForm {

	private String userName;
	private String userNameKana;
	private String depId;
	private String hireDate;
	private String mailAddress;
	private String password;
	
}
