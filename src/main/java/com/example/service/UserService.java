package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.domain.response.LoginUser;
import com.example.mapper.UserMapper;

public class UserService {
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * メールアドレスからユーザー情報を取得する.
	 * 
	 * @param mail メールアドレス
	 * @return ユーザー情報
	 */
	public LoginUser findByMailAndAuthoriry(String mail) {
		
		LoginUser loginUser = userMapper.findByMailAndAuthority(mail);
		
		if(loginUser == null) {
			Mail mailObject = new Mail();
			mailObject.setMailName(mail);
			
			User user = new User();
			user.setAuthority(0);
			
			mailObject.setUser(user);
			
			//nullならauthority番号が0のものと、mailAddressをつめたユーザー情報を返す
			return mailObject;
		} else {
			
			//nullじゃなければすべて詰まった情報を返す
			return loginUser;
		}
	}
}
