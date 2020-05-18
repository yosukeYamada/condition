package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

/**
 * ユーザー情報のドメインクラス TODO dep,postedNewsListはのちほど削除する
 * 
 * @author yuichiyasui
 */
@Data
public class User {
	/** ユーザーID */
	private Integer userId;
	/** ユーザー名 */
	private String userName;
	/** ユーザー名のふりがな */
	private String userNameKana;
	/** 部署ID */
	private Integer depId;
	/** 入社日 */
	private Timestamp hireDate;
	/** ユーザー権限 */
	private Integer authority;
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
	/** 投稿一覧 */
	private List<DailyPost> dailyPost;
	/**
	 * メールアドレスリスト
	 * 
	 * @apiNote 現状1つしか登録しないが複数利用可能にした場合を想定
	 */
	private List<Mail> mailList;
	private Dep dep;
	private List<PostedNews> postedNewsList;

}