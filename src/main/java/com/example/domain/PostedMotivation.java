package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PostedMotivation {

	private Integer postedMotivationId;
	private Integer dailyPostId;
	private Integer motivationId;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
}
