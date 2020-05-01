package com.example.domain.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ResponseUser {

	private Integer userId;
	private String userName;
	private String userNameKana;
	private Integer depId;
	private Timestamp hireDate;
	private Integer authority;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
	private String mailAddress;
	
}
