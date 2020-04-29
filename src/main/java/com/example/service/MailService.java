package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.mapper.MailAndUserMapper;
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
	
	@Autowired
	private MailAndUserMapper mailAndUserMapper;
	
	/**
	 * メールアドレスからメール情報を取得する.
	 * 
	 * @param mail メールアドレス
	 * @return メール情報
	 */
	public Mail findByMailAndAuthoriry(String mail) {
		Mail mailName = mailAndUserMapper.findByMailAndAuthority(mail);
		
		if(mailName == null) {
			return null;
		} else {
			Mail mailObj = new Mail();
			mailObj.setMailId(mailName.getMailId());
			
			User user = new User();
			user.setAuthority(mailName.getUser().getAuthority());
			
			mailObj.setUser(user);
			
			System.err.println("オブジェクト : " + mailObj);
			
			return mailObj;
		}
	}
	
	public void registerMail(User user,String mailAddress) {
		Mail mail = new Mail();
		BeanUtils.copyProperties(user, mail);
		mail.setMailName(mailAddress);
		mailMapper.insertMailAddress(mail);
	}
}