package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.mapper.MailMapper;
import com.example.mapper.ResponseUserMapper;

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
	private ResponseUserMapper responseUserMapper;
	
	/**
	 * メールアドレスからメール情報を取得する.
	 * 
	 * @param mail メールアドレス
	 * @return メール情報
	 */
	public Mail findByMailAndAuthoriry(String mail) {
		
		Mail mailObj = responseUserMapper.findUserInfo(mail);
		
		if(mailObj == null) {
			Mail mailObject = new Mail();
			mailObject.setMailName(mail);
			
			User user = new User();
			user.setAuthority(0);
			
			mailObject.setUser(user);
			
			//nullならauthority番号が0のものと、mailAddressをつめたユーザー情報を返す
			return mailObject;
		} else {
			
			//nullじゃなければすべて詰まった情報を返す
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