package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Mail;
import com.example.mapper.MailMapper;

/**
 * メールを管理するサービス.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class MailService {

	@Autowired
	private MailMapper mailMapper;
	
	/**
	 * メールアドレスからメール情報を取得する.
	 * 
	 * @param mail メールアドレス
	 * @return メール情報
	 */
	public Mail findByMail(String mail) {
		Mail mailName = mailMapper.findByMail(mail);
		if(mailName == null) {
			return null;
		} else {
			Mail mailObj = new Mail();
			mailObj.setMailId(mailName.getMailId());
			
			System.err.println("オブジェクト : " + mailObj);
			
			return mailObj;
		}
	}
}