package com.example.form;

import lombok.Data;

/**
 * 毎日のコンディション投稿内容を受け取るフォームクラス
 * 
 * @author suzukiryouhei
 *
 */
@Data
public class RegisterDailyPostForm {
	/** 投稿者のuserId */
	private Integer userId;
	/** やる気 */
	private Integer motivationId;
	/** 体調 */
	private Integer conditionId;
	/** 成果進捗 */
	private Integer performanceId;
	/** その他コメント */
	private String comment;

}
