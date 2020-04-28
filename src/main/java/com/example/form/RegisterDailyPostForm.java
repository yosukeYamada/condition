package com.example.form;

import java.util.Date;

import lombok.Data;

/**
 * 投稿内容を受け取るフォームクラス
 * @author suzukiryouhei
 *
 */
@Data
public class RegisterDailyPostForm {
	//  投稿者のuserId
	private int userId;
//	private int dailyPostId; serial？
//	private Date date; いらない？
	//  登録時間
	private Date registerDate;
	//  体調
	private String condition;
	//  成果進捗
	private String performance;
	//  やる気
	private String motivation;
	//  その他コメント
	private String comment;
}
