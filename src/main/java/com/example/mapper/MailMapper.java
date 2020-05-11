package com.example.mapper;

import java.sql.Timestamp;

import com.example.domain.Mail;

import org.apache.ibatis.annotations.Mapper;

/**
 * mailsテーブルを操作するマッパー
 * 
 * @author yuichiyasui
 */
@Mapper
public interface MailMapper {

	/**
	 * メール情報を登録するMapper.
	 * 
	 * @param mail メール情報
	 */
	public Mail insertMailAddress(Mail mail);

	/**
	 * メールアドレスで検索したユーザーのユーザー権限を変更するメソッド.
	 * 
	 * @param email       メールアドレス
	 * @param authorityId 変更する権限
	 */
	public String updateAuthorityByEmail(String email, Integer authorityId, Integer updateUserId, Timestamp updateDate);

}
