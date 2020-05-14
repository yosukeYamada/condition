package com.example.domain;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 部門情報のドメインクラス
 * 
 * @author yuichiyasui
 */
@Data
public class Dep {
	/** 部門ID */
	private Integer depId;
	/** 部門名 */
	private String depName;
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
