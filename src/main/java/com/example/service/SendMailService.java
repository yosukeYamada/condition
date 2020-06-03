package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.form.SendMailForm;

@Service
@Transactional
public class SendMailService {
	@Autowired
	private MailSender mailSender;
 
	/**
	 * 本日の未投稿者にメールを送信するサービス.
	 * @param sendMailForm
	 */
	public void sendMail(SendMailForm sendMailForm) {
		
		String IPadnPort = "condition-meter.web.app";
		String from = "rakuppo123@gmail.com";
		String title = "【Rakuppo】本日のコンディション未投稿通知";
		String content = sendMailForm.getUserName() + "さん" +"\n"+ "本日のコンディション投稿がされていません。" + "\n"+"以下のリンクにアクセスしてコンディション投稿を行いましょう。"+"\n"+"https://"+IPadnPort;
		

		SimpleMailMessage msg = new SimpleMailMessage();
		
		msg.setFrom(from);
	    msg.setTo(sendMailForm.getMail());
	    msg.setSubject(title);
	    msg.setText(content);
		mailSender.send(msg);
	}
}
