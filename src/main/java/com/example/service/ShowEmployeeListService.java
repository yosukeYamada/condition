package com.example.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.mapper.UserMapper;

/**
 * 
 * 管理者側の従業員一覧画面のサービス.
 * 
 * @author riho.ikeda
 *
 */
@Service
@Transactional
public class ShowEmployeeListService {
	
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * ユーザー情報を取得する.
	 * 
	 * @return
	 */
	public List<User>  showEmployee() {
		return userMapper.findAll();
		
	}
	

}
