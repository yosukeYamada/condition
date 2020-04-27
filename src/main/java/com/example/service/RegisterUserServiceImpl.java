package com.example.service;

import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.service.interfaces.RegisterUserService;

@Service
public class RegisterUserServiceImpl implements RegisterUserService{

	@Override
	public Integer registerUser(User user) {
		
		return 1;
	}
}
