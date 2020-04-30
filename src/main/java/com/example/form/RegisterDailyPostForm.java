package com.example.form;


import lombok.Data;

/**
 * 投稿内容を受け取るフォームクラス
 * @author suzukiryouhei
 *
 */
@Data
public class RegisterDailyPostForm {
	/** 投稿者のuserId */
	private String userId;
	/** 登録時間 */  
	private String date;
	/** 体調 */  
	private String conditionName;
	/** 成果進捗 */  
	private String performanceName;
	/** やる気 */  
	private String motivationName;
	/** その他コメント */  
	private String comment;
	
	public Integer getIntUserId() {
		return Integer.parseInt(userId);
	}
}
