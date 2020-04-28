package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Mail;
import com.example.form.MailForm;
import com.example.service.MailService;

/**
 * メールを管理するコントローラー.
 * 
 * @author iidashuhei
 *
 */
@RestController
@RequestMapping("")
public class MailController {

	@Autowired
	private MailService mailService;
	
	/**
	 * メールアドレスからメール情報を取得.
	 * 
	 * @param mail メール
	 * @return サービスへ遷移
	 */
	@CrossOrigin
	@RequestMapping("/findByMail")
	public Mail findByMail(@RequestBody MailForm mailForm) {
		
		System.err.println("メール : " + mailForm.getMailName());
		
		return mailService.findByMail(mailForm.getMailName());
	}
}
