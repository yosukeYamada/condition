package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class User {

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
	private Dep dep;
	private List<DailyPost> dailyPost;
	
}
