package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.TestMessageForm;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("")
public class RestController {

//	@CrossOrigin
	@RequestMapping("/test")
	@CrossOrigin
	public String test(@RequestBody TestMessageForm form) {
		System.out.println(form);
		return "送られてきた値は"+form.getTestMessage()+"でした！";

	}

}
