package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;
/**
 * 投稿されたやる気のドメインクラス
 */
@Data
public class PostedMotivation {
	/** 投稿されたやる気ID */
	private Integer postedMotivationId;
	/** 毎日の投稿ID */
	private Integer dailyPostId;
	/** やる気ID */
	private Integer motivationId;
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
	/** やる気 */
	private Motivation motivation;
}
