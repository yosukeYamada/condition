package com.example.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Authority;
import com.example.domain.response.LoginUser;
import com.example.form.MailForm;
import com.example.service.FindUserInfoService;

/**
 * ユーザー情報を取得するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
@RequestMapping("/api/user")
public class FindUserInfoController {
	
	@Autowired
	private FindUserInfoService userService;

	/**
	 * メールアドレスからメール情報を取得.
	 * 
	 * @param mail メールアドレス
	 * @return サービスへ遷移
	 */
	@PostMapping("/findByMailAndAuthority")
	public LoginUser findByMailAndAuthority(@RequestBody MailForm mailForm) {
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@rakus-partners.co.jp";
		String check2 = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@rakus.co.jp";
		Pattern pattern = Pattern.compile(check);
		Pattern pattern2 = Pattern.compile(check2);
		Matcher matcher = pattern.matcher(mailForm.getMail());
		Matcher matcher2 = pattern2.matcher(mailForm.getMail());
		if(matcher.matches() || matcher2.matches()) {
			return userService.findByMailAndAuthoriry(mailForm.getMail());
		} else {
			LoginUser loginUser = new LoginUser();
			loginUser.setAuthority(Authority.OUTSIDER.getAuthorityId());
			return loginUser;
		}
	}
	
}
