package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import com.example.domain.Mail;
import com.example.domain.Status;
import com.example.domain.User;
import com.example.domain.error.ExclusiveException;
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
	public void signUp(@RequestBody(required = false) @Valid SignInUserForm form) throws ExclusiveException{
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@rakus-partners.co.jp";
		String check2 = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@rakus.co.jp";
		Pattern pattern = Pattern.compile(check);
		Pattern pattern2 = Pattern.compile(check2);
		Matcher matcher = pattern.matcher(form.getMailAddress());
		Matcher matcher2 = pattern2.matcher(form.getMailAddress());
		if (matcher.matches() || matcher2.matches()) {
			registerPasswordService.registerApiUser(form);
		}else{
			throw new ExclusiveException("入力値が違法です");
		}
	}
	
	
	

	/**
	 * ユーザ登録とメール登録を行うメソッド.
	 * 
	 * @param form ユーザ登録フォーム
	 * @return ユーザ情報
	 */
	@PostMapping("/registerUser")
	public User reisterUser(@RequestBody(required = false) @Valid RegisterUserForm form)throws ExclusiveException  {
		boolean confirmDuplication = mailService.confirmMail(form.getMailAddress());
		if(confirmDuplication == true) {
		RegisterUserForm registerUserform = reMakeUserName(form);
		User user = registerUserService.registerUser(registerUserform);
		String mailAddress = form.getMailAddress();
		Mail mail = mailService.registerMail(user, mailAddress);
		List<Mail> mailList = new ArrayList<>();
		mailList.add(mail);
		user.setMailList(mailList);
		return user;
		}else {
			throw new ExclusiveException(null);
		}
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