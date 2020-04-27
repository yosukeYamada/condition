package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 投稿された体調のドメインクラス.
 */
@Data
public class PostedCondition {
	/** 投稿された体調ID */
	private Integer postedConditionId;
	/** 毎日の投稿ID */
	private Integer dailyPostId;
	/** 体調ID */
	private Integer conditionId;
	/** 登録ユーザーID */
	private Integer registerUserId;
	/** 登録日時 */
	private Timestamp registerDate;
	/** 更新ユーザーID */
	private Integer updateUserId;
	/** 更新日時 */
	private Timestamp updateDate;
	/** バージョン */
	private Integer version;
	/** ステータス */
	private Integer status;
	/** 体調 */
	private Condition condition;
}
