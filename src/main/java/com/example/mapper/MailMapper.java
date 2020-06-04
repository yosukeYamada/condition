package com.example.mapper;

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
	 * ユーザー情報更新時にメールアドレスを変更するメソッド.
	 * 
	 * @param mail メール情報
	 */
	public void updateMail(Mail mail);
	
	/**
	 * メールアドレスで検索したユーザのステータスを変更するメソッド.
	 * 
	 * @param mail メール情報
	 * @return 最新のメールVersion
	 */
	public Integer updateMailStatus(Mail mail);
		
	
	//テスト用
	public void insertMails();
	
	public Mail findByMailName(String mailName);
	
}
