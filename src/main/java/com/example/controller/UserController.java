package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.domain.response.LoginUser;
import com.example.form.MailForm;
import com.example.form.RegisterUserForm;
import com.example.service.MailService;
import com.example.service.RegisterUserServiceImpl;
import com.example.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private RegisterUserServiceImpl registerUserService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
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
			loginUser.setAuthority(3);
			return loginUser;
		}
	}
	

	/**
	 * ユーザ登録とメール登録を行うメソッド.
	 * 
	 * @param form ユーザ登録フォーム
	 * @return ユーザ情報
	 */
	@PostMapping("/registerUser")
	public User reisterUser(@RequestBody(required = false) @Valid RegisterUserForm form) {
		RegisterUserForm registerUserform = reMakeUserName(form); 
		User user = registerUserService.registerUser(registerUserform);
		String mailAddress = form.getMailAddress();
		Mail mail =  mailService.registerMail(user,mailAddress);
		List<Mail> mailList = new ArrayList<>();
		mailList.add(mail);
		user.setMailList(mailList);
		return user;
	}
	
	/**
	 * ユーザフォームの名前とふりがなからスペースを取り除くメソッド.
	 * 
	 * @param form ユーザフォーム
	 * @return 編集したユーザフォーム
	 */
	public RegisterUserForm reMakeUserName(RegisterUserForm form) {
		String userName = form.getUserName();
		String userNameKana = form.getUserNameKana();
		if(userName.contains(" ")) {
			userName.replace(" ", "");
			form.setUserName(userName);
		}
		if(userNameKana.contains(" ")) {
			userNameKana.replace(" ","");
			form.setUserNameKana(userNameKana);
		}
		return form;
	}
}