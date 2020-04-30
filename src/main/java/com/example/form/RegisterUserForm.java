package com.example.form;

import lombok.Data;

@Data
public class RegisterUserForm {

	private String userName;
	private String userNameKana;
	private String depId;
	private String hireYear;
	private String hireMonth;
	private String mailAddress;
	private String password;
	private String confirmationPassword;
	private String authorityId;

	public Integer getIntDepId() {
		return Integer.parseInt(depId);
	}

	public Integer getIntAuthorityId() {
		return Integer.parseInt(authorityId);
	}
}
