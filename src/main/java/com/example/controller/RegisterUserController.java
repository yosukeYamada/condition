package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.form.SignInUserForm;
import com.example.service.RegisterMailService;
import com.example.service.RegisterPasswordService;
import com.example.service.RegisterUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * ユーザー登録をするコントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
public class RegisterUserController {
	
	@Autowired
	private RegisterPasswordService registerPasswordService;
	
	@Autowired
	private RegisterUserService registerUserService;
	
	@Autowired
	private RegisterMailService mailService;
	
	
	/**
	 * APIの新規ユーザ登録をするメソッド.
	 * 
	 * @param form リクエストパラメータ
	 */
	@PostMapping("/signUp")
	public void signUp(@RequestBody(required = false) @Valid SignInUserForm form) {
		registerPasswordService.registerApiUser(form);
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
		Mail mail = mailService.registerMail(user, mailAddress);
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
		if (userName.contains(" ")) {
			userName.replace(" ", "");
			form.setUserName(userName);
		}
		if (userNameKana.contains(" ")) {
			userNameKana.replace(" ", "");
			form.setUserNameKana(userNameKana);
		}
		return form;
	}

}