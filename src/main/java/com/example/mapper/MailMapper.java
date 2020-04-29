package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Mail;

@Mapper
public interface MailMapper {

	/**
	 * メールアドレスが登録されているかを取得する.
	 * 
	 * @param mail メール
	 * @return メール情報
	 */
	public Mail findByMail(String mail);
	
}