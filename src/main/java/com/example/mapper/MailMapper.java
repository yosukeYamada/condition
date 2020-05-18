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
	 * @param email        メールアドレス
	 * @param authority    変更する権限
	 * @param updateUserId 更新するユーザーのID
	 * @param updateDate   更新日
	 * @return 更新されたユーザーの名前
	 */
	public String updateAuthorityByEmail(String email, Integer authority, Integer updateUserId, Timestamp updateDate);

	/**
	 * ユーザー情報更新時にメールアドレスを変更するメソッド.
	 * 
	 * @param mail メール情報
	 */
	public void updateMail(Mail mail);
	
	/**
	 * メールアドレスで検索したユーザのステータスを変更するメソッド.
	 * 
	 * @param mail メール情報
	 * @return　最新のメールVersion
	 */
	public Integer updateMailStatus(Mail mail);
		
		
	
	
}
