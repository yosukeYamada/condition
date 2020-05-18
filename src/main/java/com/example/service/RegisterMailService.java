package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.mapper.MailMapper;

/**
 * メールを管理するサービス.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class RegisterMailService {

	@Autowired
	private MailMapper mailMapper;
	
	/**
	 * メールアドレスを登録する.
	 * 
	 * @param user ユーザー
	 * @param mailAddress メールアドレス
	 * @return メールアドレス
	 */
	public Mail registerMail(User user,String mailAddress) {
		Mail mail = new Mail();
		BeanUtils.copyProperties(user, mail);
		mail.setMailName(mailAddress);
		Mail reMail = mailMapper.insertMailAddress(mail);
		return reMail;
	}
}