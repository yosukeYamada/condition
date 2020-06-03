package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.SendMailForm;
import com.example.service.SendMailService;

/**
 * メールを送信するコントローラー.
 * 
 * @author riho.ikeda
 *
 */
@RestController
@RequestMapping("/sendMail")
public class SendMailController {
	
	@Autowired
	private SendMailService sendMailService;
	
	
	
	
@RequestMapping("")
public void sendMail(@RequestBody SendMailForm sendMailForm){


	sendMailService.sendMail(sendMailForm);
	
	
}
	
	

}
