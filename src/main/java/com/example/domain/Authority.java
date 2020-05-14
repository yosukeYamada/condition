package com.example.domain;

/**
 * ユーザー権限を表す列挙型
 * 
 * @author yuichiyasui
 * @apiNote 呼び出し方の例：Authority.ADMIN.getAuthorityId()
 */
public enum Authority {
	ADMIN(1, "管理者"), USER(2, "利用者");

	/** 権限ID */
	private final Integer authorityId;
	/** 権限名 */
	private final String authorityName;

	private Authority(final Integer authorityId, final String authorityName) {
		this.authorityId = authorityId;
		this.authorityName = authorityName;
	}

	public Integer getAuthorityId() {
		return authorityId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

}
