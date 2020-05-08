package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 毎日のコンディション情報のドメインクラス
 * 
 * @author yuichiyasui
 */
@Data
public class DailyPost {

	/** 毎日の投稿のID */
	private Integer dailyPostId;
	/** ユーザーID */
	private Integer userId;
	/** 投稿日時 */
	private Timestamp date;
	/** 登録ユーザーID */
	private Integer registerUserId;
	/** 登録日 */
	private Timestamp registerDate;
	/** 更新ユーザーID */
	private Integer updateUserId;
	/** 更新日 */
	private Timestamp updateDate;
	/** バージョン */
	private Integer version;
	/** ステータス */
	private Integer status;
	/** 投稿されたやる気 */
	private PostedMotivation postedMotivation;
	/** 投稿された体調 */
	private PostedCondition postedCondition;
	/** 投稿された成果 */
	private PostedPerformance postedPerformance;
	/** 投稿されたコメント */
	private PostedComment postedComment;
}
