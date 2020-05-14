package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Dep;
import com.example.domain.Mail;
import com.example.domain.response.LoginUser;
import com.example.mapper.DepMapper;
import com.example.mapper.UserMapper;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private DepMapper depMapper;
	

	/**
	 * メールアドレスからユーザー情報を取得する.
	 * 
	 * @param mail メールアドレス
	 * @return ユーザー情報
	 */
	public LoginUser findByMailAndAuthoriry(String mail) {
		
		LoginUser loginUser = userMapper.findByMailAndAuthority(mail);
		
		//nullならauthority番号が0のものと、mailAddress、depListをつめたユーザー情報を返す
		if(loginUser == null) {
			
			List<Mail> mailList = new ArrayList<>();
			Mail newMail = new Mail();
			newMail.setMailName(mail);
			mailList.add(newMail);
			
			LoginUser newUser = new LoginUser();
			newUser.setMailList(mailList);
			newUser.setAuthority(0);
			newUser.setDepList(depMapper.findAll());
			
			return newUser;
			
			
		//nullじゃなければすべて詰まった情報を返す		
		} else {
			
			List<Dep> depList = depMapper.findAll();
			loginUser.setDepList(depList);
			
			return loginUser;
		}
	}
}