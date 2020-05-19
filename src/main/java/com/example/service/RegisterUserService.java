package com.example.service;

import java.sql.Timestamp;
import java.util.List;

import com.example.domain.Authority;
import com.example.domain.Status;
import com.example.domain.User;
import com.example.form.RegisterUserForm;
import com.example.mapper.UserMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザ登録を行うサービス.
 * 
 * @author yosuke.yamada
 *
 */
@Service
@Transactional
public class RegisterUserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * ユーザー登録処理を行うメソッド
	 * 
	 * @param form 登録するユーザー情報
	 * @return 登録したユーザー情報
	 */
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
		user.setDepId(depId);
		user.setHireDate(hireDate);
		user.setRegisterDate(registerDate);
		user.setAuthority(Authority.USER.getAuthorityId());
		user.setVersion(1);
		user.setStatus(Status.AVAILABLE.getStatusId());
		BeanUtils.copyProperties(form, user);
		Integer registerUserId = userMapper.insertUser(user);
		userMapper.updateRegisterUserId(registerUserId);
		user.setUserId(registerUserId);
		user.setRegisterUserId(registerUserId);
		return user;
	}
}