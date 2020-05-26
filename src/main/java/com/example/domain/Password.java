package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Password {

	private Integer passwordId;
	private String mailAddress;
	private String password;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
	private Integer authority;
	
	
	
}
