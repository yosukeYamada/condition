package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DailyPost {

	private Integer dailyPostId;
	private Integer userId;
	private Timestamp date;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
}
