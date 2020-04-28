package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.RegisterDailyPostForm;
import com.example.service.RegisterDailyPostServise;

@RestController
@RequestMapping("/registerDailyPost")
public class RegisterDailyPostController {
	@Autowired
	private RegisterDailyPostServise registerDailyPostServise;
	
	@PostMapping(params="registerDailyPost")
	public void registerDailyPost(@RequestBody RegisterDailyPostForm form) {
		//フォームの値をサービスに送る
		registerDailyPostServise.registerDailyPost();
	}
}
