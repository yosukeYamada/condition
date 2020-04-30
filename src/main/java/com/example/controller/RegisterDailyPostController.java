package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.RegisterDailyPostForm;
import com.example.service.RegisterDailyPostServise;

@RestController

public class RegisterDailyPostController {
	public RegisterDailyPostForm setUpForm() {
		return new RegisterDailyPostForm();
	}
	
	@Autowired
	private RegisterDailyPostServise registerDailyPostServise;
	@CrossOrigin(origins="http://localhost:8888")
	@RequestMapping(value="/registerDailyPost", method = {RequestMethod.POST})
	public void registerDailyPost(@RequestBody RegisterDailyPostForm form) {	
		// フォームの値をサービスに渡す
		System.out.println("動いてる");
		registerDailyPostServise.registerDailyPost(form);
	}
	
}
