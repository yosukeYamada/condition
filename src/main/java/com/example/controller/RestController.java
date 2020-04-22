package com.example.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.TestMessageForm;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("")
public class RestController {

	@RequestMapping("/test")
	@CrossOrigin(origins= {"https://condition-meter.web.app/"})
	public String test(@RequestBody TestMessageForm form) {
		return "送られてきた値は"+form.getTestMessage()+"でした！";

	}

}
