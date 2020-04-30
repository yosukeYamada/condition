package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.service.MailService;
import com.example.service.RegisterUserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private RegisterUserServiceImpl registerUserService;
	
	@Autowired
	private MailService mailService;
	
	@CrossOrigin(origins="http://localhost:8888")
	@PostMapping("/registerUser")
	public void registerUser(@RequestBody(required = false) RegisterUserForm form) {
		User user = registerUserService.registerUser(form);
		String mailAddress = form.getMailAddress();
		mailService.registerMail(user,mailAddress);
	}

}
