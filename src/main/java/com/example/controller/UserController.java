package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.User;
import com.example.form.RegisterUserForm;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@PostMapping(params="registerUser")
	public void registerUser(@RequestBody RegisterUserForm form) {
		
	}

}
