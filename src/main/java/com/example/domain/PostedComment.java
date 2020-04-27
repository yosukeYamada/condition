package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 投稿されたコメントのドメインクラス
 */
@Data
public class PostedComment {
	/** 投稿されたコメントID */
	private Integer postedCommentId;
	/** 毎日の投稿ID */
	private Integer dailyPostId;
	/** コメント内容 */
	private String comment;
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
}
