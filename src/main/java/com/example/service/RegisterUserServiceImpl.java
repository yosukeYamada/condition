package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.mapper.UserMapper;
import com.example.service.interfaces.RegisterUserService;

/**
 * ユーザ登録を行うサービス.
 * 
 * @author yosuke.yamada
 *
 */
@Service
public class RegisterUserServiceImpl implements RegisterUserService{

	@Autowired
	private UserMapper userMapper;
	
	
	@Override
	public Integer registerUser(User user) {
		
		return 1;
	}
	
	@Override
	public List<User> findAll(){
		List<User> userList = userMapper.findAll();
		return userList;
	}
}
