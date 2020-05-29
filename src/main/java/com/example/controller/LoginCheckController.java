package com.example.controller;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.domain.Authority;
import com.example.domain.User;
import com.example.service.FindUserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ログイン時にユーザー確認を行うコントローラークラス
 * 
 * @author yuichiyasui
 */
@RestController
public class LoginCheckController {

	@Autowired
	private FindUserInfoService userService;

	/**
	 * メールアドレスからログインユーザー情報を取得を取得するメソッド.
	 * 
	 * @param param メールアドレス
	 * @return ログインユーザー情報
	 */
	@RequestMapping("/loginCheck")
	public User loginCheck(@RequestBody Map<String, String> param) {
//		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@rakus-partners.co.jp";
//		String check2 = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@rakus.co.jp";
//		Pattern pattern = Pattern.compile(check);
//		Pattern pattern2 = Pattern.compile(check2);
//		Matcher matcher = pattern.matcher(param.get("mail"));
//		Matcher matcher2 = pattern2.matcher(param.get("mail"));
//		if (matcher.matches() || matcher2.matches()) {
			return userService.findByMailAndAuthoriry(param.get("mail"));
//		} else {
//			/** 組織外ユーザーによるログインであった場合 */
//			User user = new User();
//			user.setAuthority(Authority.OUTSIDER.getAuthorityId());
//			return user;
//		}
	}

}