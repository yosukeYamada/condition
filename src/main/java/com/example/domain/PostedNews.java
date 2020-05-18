package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 投稿されたお知らせのドメインクラス.
 * 
 * @author sakai
 *
 */
@Data
public class PostedNews {

	/** 投稿されたお知らせID */
	private Integer newsId;
	/** 投稿されたお知らせ日時 */
	private Timestamp newsDate;
	/** お知らせ内容 */
	private String newsComment;
	/** ユーザーID */
	private Integer userId;
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
