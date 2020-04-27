package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PostedCondition {

	private Integer postedConditionId;
	private Integer dailyPostId;
	private Integer conditionId;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
}
