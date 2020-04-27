package com.example.service.interfaces;

import org.springframework.stereotype.Service;

import com.example.domain.User;

@Service
public interface RegisterUserService {
	
	public Integer registerUser(User user);

}
