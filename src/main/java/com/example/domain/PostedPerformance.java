package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 投稿された成果のドメインクラス
 */
@Data
public class PostedPerformance {
	/** 投稿された成果ID */
	private Integer postedPerformanceId;
	/** 毎日の投稿ID */
	private Integer dailyPostId;
	/** 成果ID */
	private Integer performanceId;
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
	/** 成果 */
	private Performance performance;
}
