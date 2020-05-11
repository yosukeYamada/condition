package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Mail {

	private Integer mailId;
	private String mailName;
	private Integer userId;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
	
}