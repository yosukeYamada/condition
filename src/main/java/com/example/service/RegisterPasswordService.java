package com.example.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Password;
import com.example.domain.Status;
import com.example.form.SignInUserForm;
import com.example.mapper.PasswordMapper;

@Service
@Transactional
public class RegisterPasswordService {

	@Autowired
	private PasswordMapper passwordmapper;
	
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	/**
	 * 新規ユーザパスワードの登録を行うメソッド.
	 * 
	 * @param form　ユーザ情報
	 */
	public void registerApiUser(SignInUserForm form) {
		Password password = new Password();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		password.setPassword(encoder.encode(form.getPassword()));
		password.setStatus(Status.AVAILABLE.getStatusId());
		password.setVersion(1);
		password.setRegisterDate(timestamp);
		password.setRegisterUserId(0);
		password.setMailAddress(form.getMailAddress());
		Password confirmPassword = passwordmapper.load(form.getMailAddress());
		if(confirmPassword == null) {
			passwordmapper.registerPassword(password);
		}
	}
	
}
