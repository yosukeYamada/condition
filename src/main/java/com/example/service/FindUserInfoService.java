package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Authority;
import com.example.domain.Mail;
import com.example.domain.Status;
import com.example.domain.User;
import com.example.mapper.UserMapper;

/**
 * ユーザー情報を取得するサービス.
 * 
 * @author iidashuhei
 *
 */
@Service
@Transactional
public class FindUserInfoService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * メールアドレスからユーザー情報を取得する.
	 * 
	 * @param mail メールアドレス
	 * @return ユーザー情報
	 */
	public User findByMailAndAuthoriry(String mail,Integer status) {
		User loginUser = new User();
		try {
			loginUser = userMapper.findUserByMail(mail,status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// nullならauthority番号が0のものと、mailAddress、depListをつめたユーザー情報を返す
		if (loginUser == null) {
			List<Mail> mailList = new ArrayList<>();
			Mail newMail = new Mail();
			newMail.setMailName(mail);
			mailList.add(newMail);
			User newUser = new User();
			newUser.setMailList(mailList);
			newUser.setAuthority(Authority.UNREGISTERED.getAuthorityId());
			return newUser;
			
		// statusが9（削除）だったらログインをさせない
		} else if(loginUser.getStatus() == Status.DELETED.getStatusId()) {
			User deletedUser = new User();
			deletedUser.setStatus(9);
			return deletedUser;
		} else {
			/** nullじゃなければすべて詰まった情報を返す */
			return loginUser;
		}
	}
}