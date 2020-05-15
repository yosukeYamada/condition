package com.example.form;

import lombok.Data;

@Data
public class UpdateUserStatusForm {

	private String userId;
	private String userVersion;
	private String updateUserStatus;
	private String updateUserId;
	
}
