package com.example.form;

import lombok.Data;

/**
 * 毎日のコンディション投稿内容を受け取るフォームクラス
 * 
 * @author iidashuhei
 *
 */
@Data
public class EditDailyPostForm {
	
	private Integer dailyPostId;
	private Integer updateUserId;
	private Integer motivationId;
	private Integer conditionId;
	private Integer performanceId;
	private String comment;
	private Integer version;
	
}