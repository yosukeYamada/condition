package com.example.form;

import lombok.Data;

/**
 * メールを送信するフォーム.
 * 
 * @author riho.ikeda
 *
 */
@Data
public class SendMailForm {

	private String userName;
	private String mail;

}
