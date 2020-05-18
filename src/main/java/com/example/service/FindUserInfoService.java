package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.Authority;
import com.example.domain.Mail;
import com.example.domain.PostedNews;
import com.example.domain.User;
import com.example.mapper.PostedNewsMapper;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Autowired
	private PostedNewsMapper postedNewsMapper;

	/**
	 * メールアドレスからユーザー情報を取得する.
	 * 
	 * @param mail メールアドレス
	 * @return ユーザー情報
	 */
	public User findByMailAndAuthoriry(String mail) {
		User loginUser = userMapper.findByMail(mail);
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
			
		// nullじゃなければすべて詰まった情報を返す
		} else {
			/** nullじゃなければすべて詰まった情報を返す */
			List<PostedNews> postedNewsList = postedNewsMapper.findAll(); // TODO あとで整理する
			loginUser.setPostedNewsList(postedNewsList);
			return loginUser;
		}
	}
}