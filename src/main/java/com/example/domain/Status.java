package com.example.domain;

/**
 * ステータスを表す列挙型
 * 
 * @author yuichiyasui
 * @apiNote 呼び出し方の例：Status.AVALABLE.getStatusId()
 */
public enum Status {
	HOLD(1, "保留中"), AVAILABLE(2, "公開"), BAN(3, "停止"), DELETED(9, "削除");

	/** ステータスID */
	private final Integer statusId;
	/** ステータス名 */
	private final String statusName;

	private Status(final Integer statusId, final String statusName) {
		this.statusId = statusId;
		this.statusName = statusName;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public String getStatusName() {
		return statusName;
	}

}
