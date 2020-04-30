package com.example.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Mail;
import com.example.domain.User;
import com.example.form.MailForm;
import com.example.service.MailService;

/**
 * メールを管理するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
@RequestMapping("/mail")
public class MailController {

	@Autowired
	private MailService mailService;
	
	/**
	 * メールアドレスからメール情報を取得.
	 * 
	 * @param mail メール
	 * @return サービスへ遷移
	 */
	@CrossOrigin(origins="http://localhost:8888")
	@PostMapping("/findByMailAndAuthority")
	public Mail findByMailAndAuthority(@RequestBody MailForm mailForm) {
		
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@rakus-partners.co.jp";
		Pattern pattern = Pattern.compile(check);
		Matcher matcher = pattern.matcher(mailForm.getMail());
		if(matcher.matches()) {
			
			return mailService.findByMailAndAuthoriry(mailForm.getMail());
		} else {
			Mail mail = new Mail();
			User user = new User();
			user.setAuthority(3);
			mail.setUser(user);
			
			return mail;
		}
	}
}