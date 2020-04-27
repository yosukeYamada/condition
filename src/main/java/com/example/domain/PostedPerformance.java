package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PostedPerformance {

	private Integer postedPerformanceId;
	private Integer dailyPostId;
	private Integer performanceId;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
}
