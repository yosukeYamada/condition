package com.example.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.mapper.UserMapper;
import com.example.service.interfaces.RegisterUserService;

/**
 * ユーザ登録を行うサービス.
 * 
 * @author yosuke.yamada
 *
 */
@Service
@Transactional
public class RegisterUserServiceImpl implements RegisterUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User registerUser(RegisterUserForm form) {
		User user = new User();
		StringBuilder bldHireDate = new StringBuilder();
		bldHireDate.append(form.getHireYear());
		bldHireDate.append("-");
		bldHireDate.append(form.getHireMonth());
		bldHireDate.append("-01 00:00:00");
		Integer depId = form.getIntDepId();
		Timestamp hireDate = java.sql.Timestamp.valueOf(bldHireDate.toString());
		Timestamp registerDate = new Timestamp(System.currentTimeMillis());
		Integer authorityId = form.getIntAuthorityId();
		Integer version = 1;
		Integer statusId = 1;
		user.setDepId(depId);
		user.setHireDate(hireDate);
		user.setRegisterDate(registerDate);
		user.setAuthority(authorityId);
		user.setVersion(version);
		user.setStatus(statusId);
		BeanUtils.copyProperties(form, user);
		Integer registerUserId = userMapper.insertUser(user);
		userMapper.updateRegisterUserId(registerUserId);
		user.setUserId(registerUserId);
		user.setRegisterUserId(registerUserId);
		return user;
	}

	@Override
	public List<User> findAll() {
		List<User> userList = userMapper.findAll();
		return userList;
	}
}
