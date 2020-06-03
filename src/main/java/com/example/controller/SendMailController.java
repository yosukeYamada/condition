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


//	String IPadnPort = "condition-meter.web.app";
//	String from = "rakuppo123@gmail.com";
//	String title = "【Rakuppo】本日のコンディション未投稿通知";
//	String content = sendMailForm.getUserName() + "さん" +"\n"+ "本日のコンディション投稿がされていません。" + "\n"+"以下のリンクにアクセスしてコンディション投稿を行いましょう。"+"\n"+"https://"+IPadnPort;
//	
//
//	SimpleMailMessage msg = new SimpleMailMessage();
//	
//	msg.setFrom(from);
//    msg.setTo(sendMailForm.getMail());
//    msg.setSubject(title);
//    msg.setText(content);
//	mailSender.send(msg);
	sendMailService.sendMail(sendMailForm);
	
	
}
	
	

}
