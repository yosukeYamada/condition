package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class PostedComment {

	private Integer postedCommentId;
	private Integer dailyPostId;
	private String comment;
	private Integer registerUserId;
	private Timestamp registerDate;
	private Integer updateUserId;
	private Timestamp updateDate;
	private Integer version;
	private Integer status;
}
