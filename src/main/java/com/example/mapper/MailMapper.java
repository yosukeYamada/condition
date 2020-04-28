package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Mail;

@Mapper
public interface MailMapper {

	public Mail findByMail(String mail);

	/**
	 * メール情報を登録するMapper.
	 * 
	 * @param mail メール情報
	 */
	public void insertMailAddress(Mail mail);
}
