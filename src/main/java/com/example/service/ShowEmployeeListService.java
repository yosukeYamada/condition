package com.example.service;

import java.util.List;

import com.example.domain.User;
import com.example.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理者側の従業員一覧画面のサービス.
 * 
 * @author riho.ikeda
 */
@Service
@Transactional
public class ShowEmployeeListService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 従業員情報の一覧を取得するメソッド.
	 * 
	 * @param date 日付('yyyy/MM/dd')
	 * @return 引数で受け取った日付の投稿を含む従業員一覧
	 */
	public List<User> showEmployee() {
		List<User> employeeList = userMapper.findAllAndDailyPost();
		return employeeList;
	}
}